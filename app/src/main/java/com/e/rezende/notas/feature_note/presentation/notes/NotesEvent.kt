package com.e.rezende.notas.feature_note.presentation.notes

import com.e.rezende.notas.feature_note.domain.model.Note
import com.e.rezende.notas.feature_note.domain.util.NoteOder

abstract class NotesEvent {
    data class Order(val noteOder: NoteOder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}