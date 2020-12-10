package hu.unideb.todo.model



data class ToDoModel(
    var toDoId: Long,
    var title: String,
    var completed: Boolean
)
