package com.example.mobilesecuritynotes

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilesecuritynotes.adapters.MemoryNotesAdapter
import com.example.mobilesecuritynotes.viewmodels.NotesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {
    private lateinit var notesViewModel: NotesViewModel

    private lateinit var rvNotes: RecyclerView
    private lateinit var createButton: FloatingActionButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
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
//            this.addNote()
            val intent = Intent(this@HomeActivity, NoteActivity::class.java)
//            intent.putExtra("noteItem", )
            startActivity(intent)
        }
    }

    private fun addNote() {
        this.notesViewModel.addNote()
    }
}
