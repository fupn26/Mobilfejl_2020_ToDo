package hu.unideb.todo.model

import hu.unideb.todo.database.ToDoEntity

data class ToDoModel(
    var toDoId: Long?,
    var title: String,
    var completed: Boolean
)

fun ToDoModel.asToDoEntity() = ToDoEntity(
    toDoId = this.toDoId,
    title = this.title,
    completed = this.completed
)
