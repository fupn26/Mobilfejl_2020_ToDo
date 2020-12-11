package hu.unideb.todo.util

import android.content.Context
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import hu.unideb.todo.R
import hu.unideb.todo.databinding.TodoItemViewBinding
import hu.unideb.todo.model.ToDoModel
import org.jetbrains.annotations.NotNull

class ToDoItemViewHolder(val binding: @NotNull TodoItemViewBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ToDoModel) {
        binding.todo = item
        binding.executePendingBindings()
    }
}

@BindingAdapter("toDoIdString")
fun TextView.setToDoIdAsString(item: ToDoModel) {
    text = item.toDoId.toString()
}

@BindingAdapter("toDoCardBackgroundColor")
fun MaterialCardView.setCardBackgroundColorFromBoolean(item: ToDoModel) {
    if (item.completed)
        setCardBackgroundColor(getColor(context, R.color.lightGreen))
    else
        setCardBackgroundColor(getColor(context, R.color.lightRed))
}