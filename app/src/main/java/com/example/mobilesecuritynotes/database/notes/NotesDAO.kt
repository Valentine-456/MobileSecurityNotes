package com.example.mobilesecuritynotes.database.notes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createNote(note: NotesEntity)

    @Query("Select * from notes order by updated_at desc")
    fun getAllNotes(): LiveData<List<NotesEntity>>

    @Query("Select * from notes where note_id = :noteId")
    fun getNoteItem(noteId: Int): NotesEntity

    @Query("Select * from notes order by updated_at desc limit 1")
    fun getLastModifiedNoteItem(): NotesEntity

    @Update
    suspend fun updateNote(note: NotesEntity)

    @Delete
    suspend fun deleteNote(note: NotesEntity)
}
