package com.example.mobilesecuritynotes

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MemoryNotesAdapter(private val context: Context, private val  number: Int) : RecyclerView.Adapter<MemoryNotesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.note_card, parent, false);
        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position);
    }

    override fun getItemCount() = number;

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageButton = itemView.findViewById<ImageButton>(R.id.imageButton);
        private val title = itemView.findViewById<TextView>(R.id.textView);

        fun bind(position: Int) {
            imageButton.setOnClickListener {
                Log.i("ImageButton", "Hello");
            }
        }
    };
}
