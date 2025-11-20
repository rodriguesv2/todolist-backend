package br.com.rubensrodrigues.todolist.todo.api

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateTodoRequest(
    @field:NotBlank
    val title: String,

    val description: String? = null,

    @field:NotNull
    val userId: Long
)