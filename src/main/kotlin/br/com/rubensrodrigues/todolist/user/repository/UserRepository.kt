package br.com.rubensrodrigues.todolist.user.repository

import br.com.rubensrodrigues.todolist.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
}