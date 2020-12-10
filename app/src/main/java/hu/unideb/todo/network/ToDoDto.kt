package hu.unideb.todo.network

import com.squareup.moshi.JsonClass
import hu.unideb.todo.database.ToDoEntity

@JsonClass(generateAdapter = true)
data class ToDoDtoContainer(val toDos: List<ToDoDto>)

@JsonClass(generateAdapter = true)
data class ToDoDto(
    val userId: Long,
    val id: Long,
    val title: String,
    val completed: Boolean
)

fun ToDoDtoContainer.asDomainModel(): List<ToDoEntity> {
    return toDos.map {
        ToDoEntity(
            toDoId = it.id,
            title = it.title,
            completed = it.completed
        )
    }
}