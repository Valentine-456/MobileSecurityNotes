package com.example.mobilesecuritynotes.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mobilesecuritynotes.adapters.MemoryNotesAdapter
import com.example.mobilesecuritynotes.databinding.ActivityHomeBinding
import com.example.mobilesecuritynotes.viewmodels.NotesViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var binding: ActivityHomeBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )

        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        val adapter = MemoryNotesAdapter(this, 0)
        binding.RVNotes.adapter = adapter
        binding.RVNotes.setHasFixedSize(true)
        binding.RVNotes.layoutManager = GridLayoutManager(this, 2)

        notesViewModel.data.observe(
            this,
            Observer { noteItem ->
                adapter.setData(noteItem)
            }
        )

        binding.CreateButton.setOnClickListener {
            this.notesViewModel.addNote()
            Thread.sleep(100)
            val intent = Intent(this@HomeActivity, NoteActivity::class.java)
            val newNoteItem = this.notesViewModel.getLastModifiedNote()
            intent.putExtra("noteItemId", newNoteItem.note_id)
            startActivity(intent)
        }
    }
}
