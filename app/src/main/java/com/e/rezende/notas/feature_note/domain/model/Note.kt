package com.e.rezende.notas.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.e.rezende.notas.ui.theme.BabyBlue
import com.e.rezende.notas.ui.theme.DarkGray
import com.e.rezende.notas.ui.theme.RedOrange
import com.e.rezende.notas.ui.theme.RedPink
import com.e.rezende.notas.ui.theme.Violet

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String): Exception(message)
