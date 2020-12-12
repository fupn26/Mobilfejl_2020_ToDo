package hu.unideb.todo.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import hu.unideb.todo.databinding.TodoItemViewBinding
import hu.unideb.todo.model.ToDoModel

class ToDoAdapter(val clickListener: ToDoListener) : ListAdapter<ToDoModel, ToDoItemViewHolder>(ToDoDiffCallback()) {

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
        holder.bind(item, clickListener)
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

class ToDoListener(val clickListener: (toDoId: Long) -> Unit) {
    fun onClick(toDo: ToDoModel) = toDo.toDoId?.let { clickListener(it) }
}

sealed class DataItem {
    abstract val id: Long

    data class ToDoItem(val toDo: ToDoModel): DataItem() {
        override val id: Long = toDo.toDoId!!
    }

    object Header: DataItem() {
        override val id: Long
            get() = Long.MIN_VALUE
    }
}