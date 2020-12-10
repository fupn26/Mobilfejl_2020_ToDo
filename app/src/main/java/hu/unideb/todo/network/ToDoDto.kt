package hu.unideb.todo.network

import hu.unideb.todo.database.ToDoEntity
import hu.unideb.todo.model.ToDoModel

data class ToDoDto(
    val userId: Long,
    val id: Long,
    val title: String,
    val completed: Boolean
)

fun List<ToDoDto>.asDomainModel(): List<ToDoModel> {
    return map {
        ToDoModel(
            toDoId = it.id,
            title = it.title,
            completed = it.completed
        )
    }
}