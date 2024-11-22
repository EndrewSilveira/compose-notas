package com.e.rezende.notas.feature_note.presentation.notes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.e.rezende.notas.feature_note.domain.util.NoteOder
import com.e.rezende.notas.feature_note.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOder: NoteOder = NoteOder.Date(orderType = OrderType.Descending),
    onOrderChange: (NoteOder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Title",
                selected = noteOder is NoteOder.Title,
                onSelect = {
                    onOrderChange(NoteOder.Title(noteOder.oderType))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected = noteOder is NoteOder.Date,
                onSelect = {
                    onOrderChange(NoteOder.Date(noteOder.oderType))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Color",
                selected = noteOder is NoteOder.Color,
                onSelect = {
                    onOrderChange(NoteOder.Color(noteOder.oderType))
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = noteOder.oderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(noteOder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = noteOder.oderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(noteOder.copy(OrderType.Descending))
                }
            )
        }
    }
}