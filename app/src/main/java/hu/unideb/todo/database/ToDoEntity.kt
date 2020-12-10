package hu.unideb.todo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class ToDoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var toDoId: Long = 0L,

    var title: String = "",

    var completed: Boolean = false
)
