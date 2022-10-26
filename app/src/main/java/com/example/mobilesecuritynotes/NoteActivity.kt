package com.example.mobilesecuritynotes

import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mobilesecuritynotes.database.notes.NotesEntity
import com.example.mobilesecuritynotes.viewmodels.NotesViewModel
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : AppCompatActivity() {
    private lateinit var notesViewModel: NotesViewModel

    private lateinit var noteContent: EditText
    private lateinit var noteTitleText: EditText
    private lateinit var noteDateText: TextView
    private lateinit var deleteButton: Button
    private lateinit var saveButton: Button

    private var loc: Locale = Locale("en", "US")
    private var pattern = "MMM dd, yyyy hh:mm"
    private var simpleDateFormat: SimpleDateFormat = SimpleDateFormat(pattern, loc)

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
        noteDateText.text = simpleDateFormat.format(Date(noteItem.updated_at))

        deleteButton.setOnClickListener {
            notesViewModel.deleteNoteById(noteItem.note_id)
            super.finish()
        }

        saveButton.setOnClickListener {
            val updatedNote = NotesEntity(
                noteItem.note_id,
                noteTitleText.text.toString(),
                Date().toString(),
                noteContent.text.toString()
            )
            notesViewModel.updateNote(updatedNote)
            super.finish()
        }
    }
}
