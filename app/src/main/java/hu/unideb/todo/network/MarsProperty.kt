package hu.unideb.todo.network

data class MarsProperty(
    val userId: Long,
    val id: Long,
    val title: String,
    val completed: Boolean
)