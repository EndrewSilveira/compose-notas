package com.e.rezende.notas.feature_note.presentation.util

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object NotesScreen: Screen()

    @Serializable
    data class AddEditNoteScreen(
        val noteId : Int? = -1,
        val noteColor : Int = -1,
    ): Screen()
}