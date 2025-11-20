package br.com.rubensrodrigues.todolist.user.domain

class UserAlreadyExistsException(
    username: String
) : RuntimeException("Username '$username' already exists")