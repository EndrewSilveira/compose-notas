package com.e.rezende.notas.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.e.rezende.notas.base.BaseViewModel
import com.e.rezende.notas.feature_note.domain.model.Note
import com.e.rezende.notas.feature_note.domain.use_case.AddNoteUseCase
import com.e.rezende.notas.feature_note.domain.use_case.DeleteNoteUseCase
import com.e.rezende.notas.feature_note.domain.use_case.GetNotesUseCase
import com.e.rezende.notas.feature_note.domain.util.NoteOder
import com.e.rezende.notas.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val getNotesUseCase: GetNotesUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : BaseViewModel(coroutineDispatcher) {

    private val _state = mutableStateOf(NoteState())
    val state: State<NoteState> = _state

    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: Job? = null

    init {
        getNotes(NoteOder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> {
                if (state.value.noteOder::class == event.noteOder::class &&
                    state.value.noteOder.oderType == event.noteOder.oderType
                ) {
                    return
                }

                getNotes(event.noteOder)
            }

            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    deleteNoteUseCase.invoke(event.note)
                    recentlyDeletedNote = event.note
                }
            }

            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    addNoteUseCase.invoke(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }

            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOder: NoteOder) {
        getNotesJob?.cancel()
        getNotesJob = getNotesUseCase.invoke(noteOder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOder = noteOder
                )
            }
            .launchIn(viewModelScope)
    }
}