package br.com.rubensrodrigues.todolist.todo.domain

class UserNotFoundException(
    username: String
) : RuntimeException("User with username '$username' not found")