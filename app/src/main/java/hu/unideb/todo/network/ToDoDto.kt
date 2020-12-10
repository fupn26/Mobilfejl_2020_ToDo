package hu.unideb.todo.network

data class ToDoDto(
    val userId: Long,
    val id: Long,
    val title: String,
    val completed: Boolean
)