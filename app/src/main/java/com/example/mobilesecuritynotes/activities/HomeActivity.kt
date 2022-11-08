package com.example.mobilesecuritynotes.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilesecuritynotes.R
import com.example.mobilesecuritynotes.adapters.MemoryNotesAdapter
import com.example.mobilesecuritynotes.viewmodels.NotesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {
    private lateinit var notesViewModel: NotesViewModel

    private lateinit var rvNotes: RecyclerView
    private lateinit var createButton: FloatingActionButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        this.rvNotes = findViewById(R.id.rvNotes)
        this.createButton = findViewById(R.id.createButton)

        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        val adapter = MemoryNotesAdapter(this, 0)
        rvNotes.adapter = adapter
        rvNotes.setHasFixedSize(true)
        rvNotes.layoutManager = GridLayoutManager(this, 2)

        notesViewModel.data.observe(
            this,
            Observer { noteItem ->
                adapter.setData(noteItem)
            }
        )

        createButton.setOnClickListener {
            this.notesViewModel.addNote()
            Thread.sleep(100)
            val intent = Intent(this@HomeActivity, NoteActivity::class.java)
            val newNoteItem = this.notesViewModel.getLastModifiedNote()
            intent.putExtra("noteItemId", newNoteItem.note_id)
            startActivity(intent)
        }
    }
}
