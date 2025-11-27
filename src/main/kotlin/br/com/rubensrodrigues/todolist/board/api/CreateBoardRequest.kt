package br.com.rubensrodrigues.todolist.board.api

data class CreateBoardRequest(
    val name: String,
    val description: String?
)