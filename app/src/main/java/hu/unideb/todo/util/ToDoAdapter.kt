package hu.unideb.todo.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import hu.unideb.todo.R
import hu.unideb.todo.model.ToDoModel

class ToDoAdapter : ListAdapter<ToDoModel, ToDoItemViewHolder>(ToDoDiffCallback()){

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): ToDoItemViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.todo_item_view,
                parent, false)
        return ToDoItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.idText.text = item.toDoId.toString()
        holder.title.text = item.title

        if (item.completed)
            holder.container.setBackgroundColor(R.color.lightGreen)
        else
            holder.container.setBackgroundColor(R.color.lightRed)
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