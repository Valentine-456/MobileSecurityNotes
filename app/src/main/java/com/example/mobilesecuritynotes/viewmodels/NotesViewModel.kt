package com.example.mobilesecuritynotes.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mobilesecuritynotes.database.DataBase
import com.example.mobilesecuritynotes.database.notes.NotesEntity
import com.example.mobilesecuritynotes.repositories.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    var data: LiveData<List<NotesEntity>>
    private var repository: NotesRepository

    init {
        val dao = DataBase.getDataBase(application).notesDao()
        this.repository = NotesRepository(dao)
        data = repository.allNotes
    }

    fun addNote() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createOne()
        }
    }

    fun getNoteById(noteId: Int): NotesEntity {
        var note = NotesEntity()
        runBlocking(Dispatchers.IO) {
            note = repository.getNoteById(noteId)
        }
        return note
    }

    fun getLastModifiedNote(): NotesEntity {
        var note = NotesEntity()
        runBlocking(Dispatchers.IO) {
            note = repository.getLastModifiedNote()
        }
        return note
    }

    fun deleteNoteById(noteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteOne(noteId)
        }
    }

    fun updateNote(note: NotesEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateOne(note)
        }
    }
}
