package br.com.rubensrodrigues.todolist.todo.api

import br.com.rubensrodrigues.todolist.todo.domain.TodoStatus

data class TodoResponse(
    val id: Long,
    val title: String,
    val description: String?,
    val status: TodoStatus,
    val ownerId: Long,
    val createdAt: String,
    val completedAt: String?
)