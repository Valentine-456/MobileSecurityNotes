package com.example.mobilesecuritynotes.activities

import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mobilesecuritynotes.R
import com.example.mobilesecuritynotes.database.notes.NotesEntity
import com.example.mobilesecuritynotes.utils.TimeFormatter
import com.example.mobilesecuritynotes.viewmodels.NotesViewModel
import java.util.*

class NoteActivity : AppCompatActivity() {
    private lateinit var notesViewModel: NotesViewModel

    private lateinit var noteContent: EditText
    private lateinit var noteTitleText: EditText
    private lateinit var noteDateText: TextView
    private lateinit var deleteButton: Button
    private lateinit var saveButton: Button

    private val formatter = TimeFormatter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        this.saveButton = findViewById(R.id.SaveButton)
        this.deleteButton = findViewById(R.id.DeleteButton)
        this.noteContent = findViewById(R.id.NoteContentText)
        this.noteDateText = findViewById(R.id.NoteDateText)
        this.noteTitleText = findViewById(R.id.NoteTitleText)

        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        val noteItemId = intent.getIntExtra("noteItemId", 0)
        val noteItem: NotesEntity = notesViewModel.getNoteById(noteItemId)

        noteContent.text = Editable.Factory.getInstance().newEditable(noteItem.content)
        noteTitleText.text = Editable.Factory.getInstance().newEditable(noteItem.title)
        noteDateText.text = formatter.getNoteDateText(noteItem.updated_at)

        deleteButton.setOnClickListener {
            notesViewModel.deleteNoteById(noteItem.note_id)
            super.finish()
        }

        saveButton.setOnClickListener {
            val updatedNote = NotesEntity(
                noteItem.note_id,
                noteTitleText.text.toString(),
                Date().time,
                noteContent.text.toString()
            )
            notesViewModel.updateNote(updatedNote)
            super.finish()
        }
    }
}
