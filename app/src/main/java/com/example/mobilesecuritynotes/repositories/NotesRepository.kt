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

    fun getNoteById(noteId: Int): NotesEntity {
        return this.notesDAO.getNoteItem(noteId)
    }

    fun getLastModifiedNote(): NotesEntity {
        return this.notesDAO.getLastModifiedNoteItem()
    }

    suspend fun deleteOne(noteId: Int) {
        val note = this.getNoteById(noteId)
        notesDAO.deleteNote(note)
    }

    suspend fun updateOne(updatedNote: NotesEntity) {
        notesDAO.updateNote(updatedNote)
    }
}
