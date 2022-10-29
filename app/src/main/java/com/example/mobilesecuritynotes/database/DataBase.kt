package com.example.mobilesecuritynotes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobilesecuritynotes.database.notes.NotesDAO
import com.example.mobilesecuritynotes.database.notes.NotesEntity

@Database(entities = [NotesEntity::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract fun notesDao(): NotesDAO

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null

        fun getDataBase(context: Context): DataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "mobile_sec_notes_app"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
