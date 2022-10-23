package com.example.mobilesecuritynotes.database.notes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createNote(note: NotesEntity)

    @Query("Select * from notes order by updated_at desc")
    fun getAllNotes(): LiveData<List<NotesEntity>>
}
