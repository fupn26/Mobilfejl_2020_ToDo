package hu.unideb.todo.util

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.google.android.material.card.MaterialCardView
import hu.unideb.todo.R
import hu.unideb.todo.model.ToDoModel

class ToDoAdapter : ListAdapter<ToDoModel, ToDoItemViewHolder>(ToDoDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ToDoItemViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(
                R.layout.todo_item_view,
                parent, false
            )
        return ToDoItemViewHolder(view as MaterialCardView)
    }

    override fun onBindViewHolder(holder: ToDoItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.idText.text = item.toDoId.toString()
        holder.title.text = item.title

        if (item.completed)
            (holder.itemView as MaterialCardView).setCardBackgroundColor(getColor(holder.context, R.color.lightGreen))
        else
            (holder.itemView as MaterialCardView).setCardBackgroundColor(getColor(holder.context, R.color.lightRed))
    }

}

class ToDoDiffCallback : DiffUtil.ItemCallback<ToDoModel>() {
    override fun areItemsTheSame(oldItem: ToDoModel, newItem: ToDoModel): Boolean {
        return oldItem.toDoId == newItem.toDoId;
    }

    override fun areContentsTheSame(oldItem: ToDoModel, newItem: ToDoModel): Boolean {
        return oldItem == newItem
    }

}