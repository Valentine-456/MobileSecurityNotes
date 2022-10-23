package com.example.mobilesecuritynotes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {

    private lateinit var rvNotes: RecyclerView
    private lateinit var createButton: FloatingActionButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        this.rvNotes = findViewById(R.id.rvNotes)

        this.createButton = findViewById(R.id.createButton)

        rvNotes.adapter = MemoryNotesAdapter(this, 15)
        rvNotes.setHasFixedSize(true)
        rvNotes.layoutManager = GridLayoutManager(this, 2)
    }
}
