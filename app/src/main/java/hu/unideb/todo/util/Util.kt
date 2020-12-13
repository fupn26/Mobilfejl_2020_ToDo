package hu.unideb.todo.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
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
    fun bind(item: ToDoModel, clickListener: ToDoListener) {
        binding.toDo = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ToDoItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = TodoItemViewBinding.inflate(layoutInflater, parent, false)
            return ToDoItemViewHolder(binding)
        }
    }
}

class TextViewHolder(view: View): RecyclerView.ViewHolder(view) {
    companion object {
        fun from(parent: ViewGroup): TextViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.todo_list_header, parent, false)
            return TextViewHolder(view)
        }
    }
}

@BindingAdapter("toDoIdString")
fun TextView.setToDoIdAsString(item: ToDoModel?) {
    item?.let {
        text = item.toDoId.toString()
    }
}

@BindingAdapter("toDoCardBackgroundColor")
fun MaterialCardView.setCardBackgroundColorFromBoolean(item: ToDoModel?) {
    item?.let {
        if (item.completed)
            setCardBackgroundColor(getColor(context, R.color.lightGreen))
        else
            setCardBackgroundColor(getColor(context, R.color.lightRed))
    }
}

@BindingAdapter("chosenItemSetInSpinner")
fun Spinner.setChosenItemInSpinner(item: ToDoModel?) {
    item?.let {
        if (item.completed)
            setSelection(1)
        else
            setSelection(0)
    }
}