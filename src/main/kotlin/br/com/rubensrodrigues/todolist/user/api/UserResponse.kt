package br.com.rubensrodrigues.todolist.user.api

data class UserResponse(
    val id: Long,
    val username: String,
    val createdAt: String
)