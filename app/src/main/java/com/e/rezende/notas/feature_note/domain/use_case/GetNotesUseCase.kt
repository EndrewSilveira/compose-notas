package com.e.rezende.notas.feature_note.domain.use_case

import com.e.rezende.notas.feature_note.domain.model.Note
import com.e.rezende.notas.feature_note.domain.repository.NoteRepository
import com.e.rezende.notas.feature_note.domain.util.NoteOder
import com.e.rezende.notas.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) {

    operator fun invoke(
        noteOder: NoteOder = NoteOder.Date(OrderType.Descending)
    ): Flow<List<Note>> {

        return repository.getNotes().map { notes ->
            when (noteOder.oderType) {
                is OrderType.Ascending -> {
                    when (noteOder) {
                        is NoteOder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOder.Date -> notes.sortedBy { it.timestamp }
                        is NoteOder.Color -> notes.sortedBy { it.color }
                    }
                }

                is OrderType.Descending -> {
                    when (noteOder) {
                        is NoteOder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOder.Date -> notes.sortedByDescending { it.timestamp }
                        is NoteOder.Color -> notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}