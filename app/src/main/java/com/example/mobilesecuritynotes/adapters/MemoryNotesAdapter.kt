package com.example.mobilesecuritynotes.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilesecuritynotes.NoteActivity
import com.example.mobilesecuritynotes.R
import com.example.mobilesecuritynotes.database.notes.NotesEntity
import com.example.mobilesecuritynotes.utils.TimeFormatter
import java.util.*

class MemoryNotesAdapter(private val context: Context, private val number: Int) :
    RecyclerView.Adapter<MemoryNotesAdapter.ViewHolder>() {

    private var notesList = emptyList<NotesEntity>()
    private val formatter = TimeFormatter()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.note_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = notesList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(notes: List<NotesEntity>) {
        this.notesList = notes
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageButton = itemView.findViewById<ImageButton>(R.id.imageButton)
        private var title = itemView.findViewById<TextView>(R.id.noteTitle)
        private var date = itemView.findViewById<TextView>(R.id.noteDate)

        fun bind(position: Int) {
            this.title.text = notesList[position].title
            this.imageButton.tag = notesList[position]
            this.date.text = formatter.getNoteDateText(notesList[position].updated_at)

            imageButton.setOnClickListener {
                val intent = Intent(context, NoteActivity::class.java)
                intent.putExtra("noteItemId", notesList[position].note_id)
                context.startActivity(intent)
            }
        }
    }
}
