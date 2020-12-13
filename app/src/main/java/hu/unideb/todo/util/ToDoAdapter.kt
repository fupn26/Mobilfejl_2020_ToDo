package hu.unideb.todo.util

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.unideb.todo.databinding.TodoItemViewBinding
import hu.unideb.todo.model.ToDoModel
import hu.unideb.todo.util.TextViewHolder.Companion.from
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private val ITEM_VIEW_TYPE_HEADER = 0
private val ITEM_VIEW_TYPE_ITEM = 1

class ToDoAdapter(val clickListener: ToDoListener) : ListAdapter<DataItem, RecyclerView.ViewHolder>(ToDoDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ToDoItemViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ToDoItemViewHolder -> {
                val item = getItem(position) as DataItem.ToDoItem
                holder.bind(item.toDo, clickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.ToDoItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    fun addHeaderAndSubmitList(list: List<ToDoModel>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(DataItem.Header)
                else -> listOf(DataItem.Header) + list.map { DataItem.ToDoItem(it) }
            }

            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

}

class ToDoDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id;
    }
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
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