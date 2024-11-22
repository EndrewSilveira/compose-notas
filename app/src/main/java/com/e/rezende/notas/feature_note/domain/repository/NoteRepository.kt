package com.e.rezende.notas.feature_note.domain.repository

import com.e.rezende.notas.feature_note.data.data_source.NoteDatabase
import com.e.rezende.notas.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDatabase: NoteDatabase
) {
    fun getNotes(): Flow<List<Note>> {
        return noteDatabase.noteDao.getNotes()
    }

    suspend fun getNoteById(id: Int): Note? {
        return noteDatabase.noteDao.getNoteById(id)
    }

    suspend fun insertNote(note: Note) {
        noteDatabase.noteDao.insertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDatabase.noteDao.deleteNote(note)
    }

}