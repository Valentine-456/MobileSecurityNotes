package com.example.mobilesecuritynotes

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilesecuritynotes.database.notes.NotesEntity

class MemoryNotesAdapter(private val context: Context, private val number: Int) :
    RecyclerView.Adapter<MemoryNotesAdapter.ViewHolder>() {

    private var notesList = emptyList<NotesEntity>()

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
        private var title = itemView.findViewById<TextView>(R.id.textView)

        fun bind(position: Int) {
            this.title.text = notesList[position].updated_at
            imageButton.setOnClickListener {
                Log.i("ImageButton", "Hello")
            }
        }
    }
}
