package com.example.mobilesecuritynotes

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mobilesecuritynotes.viewmodels.NotesViewModel

class NoteActivity : AppCompatActivity() {
    private lateinit var notesViewModel: NotesViewModel

    private lateinit var noteContent: EditText
    private lateinit var noteTitleText: EditText
    private lateinit var noteDateText: TextView
    private lateinit var deleteButton: Button
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        this.saveButton = findViewById(R.id.SaveButton)
        this.deleteButton = findViewById(R.id.DeleteButton)
        this.noteContent = findViewById(R.id.NoteContentText)
        this.noteDateText = findViewById(R.id.NoteDateText)
        this.noteTitleText = findViewById(R.id.NoteTitleText)

        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        val noteItemId = getIntent().getIntExtra("noteItemId", 0)
        Toast.makeText(this, noteItemId.toString(), Toast.LENGTH_SHORT).show()
    }
}
