package com.example.mobilesecuritynotes.database.notes

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "notes")
data class NotesEntity(
    @PrimaryKey(autoGenerate = true)
    var note_id: Int = 0,
    var title: String = "",
    var updated_at: Long = Date().time,
    var content: String = ""
) : Serializable
