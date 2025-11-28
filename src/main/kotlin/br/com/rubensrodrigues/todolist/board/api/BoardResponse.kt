package br.com.rubensrodrigues.todolist.board.api

data class BoardResponse(
    val id: Long,
    val name: String,
    val description: String?,
    val createdAt: String,
    val updatedAt: String,
)
