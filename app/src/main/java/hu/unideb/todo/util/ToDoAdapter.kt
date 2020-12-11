package hu.unideb.todo.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.google.android.material.card.MaterialCardView
import hu.unideb.todo.R
import hu.unideb.todo.databinding.TodoItemViewBinding
import hu.unideb.todo.model.ToDoModel

class ToDoAdapter : ListAdapter<ToDoModel, ToDoItemViewHolder>(ToDoDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ToDoItemViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context)
        val binding =
            TodoItemViewBinding.inflate(layoutInflater, parent, false)

        return ToDoItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
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

class ToDoListener() {

}