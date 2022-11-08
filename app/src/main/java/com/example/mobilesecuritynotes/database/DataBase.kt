package com.example.mobilesecuritynotes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobilesecuritynotes.database.notes.NotesDAO
import com.example.mobilesecuritynotes.database.notes.NotesEntity
import com.example.mobilesecuritynotes.repositories.AuthRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Database(entities = [NotesEntity::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract fun notesDao(): NotesDAO

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null
        private lateinit var authRepository: AuthRepository

        fun getDataBase(context: Context): DataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance

            this.authRepository = AuthRepository(context.applicationContext)

            synchronized(this) {
                val passphrase = SQLiteDatabase.getBytes(
                    this.authRepository.getDBPassword().toCharArray()
                )
                val factory = SupportFactory(passphrase)

                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "mobile_sec_notes.db"
                )
                builder.openHelperFactory(factory)

                val instance = builder.build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
