package com.e.rezende.notas.feature_note.presentation.notes

import com.e.rezende.notas.feature_note.domain.model.Note
import com.e.rezende.notas.feature_note.domain.util.NoteOder
import com.e.rezende.notas.feature_note.domain.util.OrderType

data class NoteState (
    val notes: List<Note> = emptyList(),
    val noteOder: NoteOder = NoteOder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)