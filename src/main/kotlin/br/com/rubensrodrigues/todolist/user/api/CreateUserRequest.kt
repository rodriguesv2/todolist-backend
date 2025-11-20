package br.com.rubensrodrigues.todolist.user.api

import jakarta.validation.constraints.NotBlank

data class CreateUserRequest (
    @field:NotBlank
    val username: String,

    @field:NotBlank
    val password: String,
)