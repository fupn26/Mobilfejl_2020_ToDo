package hu.unideb.todo.network

import com.squareup.moshi.JsonClass
import hu.unideb.todo.model.ToDoModel

@JsonClass(generateAdapter = true)
data class ToDoDtoContainer(val toDos: List<ToDoDto>)

@JsonClass(generateAdapter = true)
data class ToDoDto(
    val userId: Long,
    val id: Long,
    val title: String,
    val completed: Boolean
)

fun ToDoDtoContainer.asDomainModel(): List<ToDoModel> {
    return toDos.map {
        ToDoModel(
            toDoId = it.id,
            title = it.title,
            completed = it.completed
        )
    }
}