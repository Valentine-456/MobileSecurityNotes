package com.example.mobilesecuritynotes.repositories

import androidx.lifecycle.LiveData
import com.example.mobilesecuritynotes.database.notes.NotesDAO
import com.example.mobilesecuritynotes.database.notes.NotesEntity

class NotesRepository(private val notesDAO: NotesDAO) {
    val allNotes: LiveData<List<NotesEntity>> = notesDAO.getAllNotes()

    suspend fun createOne() {
        val item = NotesEntity()
        this.notesDAO.createNote(item)
    }
}
