package hu.unideb.todo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import hu.unideb.todo.model.ToDoModel

@Entity(tableName = "todo_table")
data class ToDoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var toDoId: Long? = 0L,

    var title: String = "",

    var completed: Boolean = false
)

fun ToDoEntity.asDomainModel() = ToDoModel(
    toDoId = this.toDoId,
    title = this.title,
    completed = this.completed
)

fun List<ToDoEntity>.asDomainModel(): List<ToDoModel> {
    return map {
        ToDoModel(
            toDoId = it.toDoId,
            title = it.title,
            completed = it.completed
        )
    }
}