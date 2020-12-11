package hu.unideb.todo.util

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import hu.unideb.todo.R

class ToDoItemViewHolder(val view: MaterialCardView): RecyclerView.ViewHolder(view) {
    val context: Context = view.context
    val idText: TextView = view.findViewById(R.id.id_text)
    val title: TextView = view.findViewById(R.id.title_text)
}