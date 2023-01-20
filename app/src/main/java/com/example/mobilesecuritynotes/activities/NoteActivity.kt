package com.example.mobilesecuritynotes.activities

import android.os.Bundle
import android.text.Editable
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mobilesecuritynotes.database.notes.NotesEntity
import com.example.mobilesecuritynotes.databinding.ActivityNoteBinding
import com.example.mobilesecuritynotes.utils.TimeFormatter
import com.example.mobilesecuritynotes.viewmodels.NotesViewModel
import java.util.*

class NoteActivity : AppCompatActivity() {
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var binding: ActivityNoteBinding
    private val formatter = TimeFormatter()

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )

        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        val noteItemId = intent.getIntExtra("noteItemId", 0)
        val noteItem: NotesEntity = notesViewModel.getNoteById(noteItemId)

        binding.NoteContentText.text = Editable.Factory.getInstance().newEditable(noteItem.content)
        binding.NoteTitleText.text = Editable.Factory.getInstance().newEditable(noteItem.title)
        binding.NoteDateText.text = formatter.getNoteDateText(noteItem.updated_at)

        binding.DeleteButton.setOnClickListener {
            notesViewModel.deleteNoteById(noteItem.note_id)
            super.finish()
        }

        binding.SaveButton.setOnClickListener {
            val updatedNote = NotesEntity(
                noteItem.note_id,
                binding.NoteTitleText.text.toString(),
                Date().time,
                binding.NoteContentText.text.toString()
            )
            notesViewModel.updateNote(updatedNote)
            super.finish()
        }
    }
}
