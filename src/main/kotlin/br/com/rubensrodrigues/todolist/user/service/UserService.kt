package br.com.rubensrodrigues.todolist.user.service

import br.com.rubensrodrigues.todolist.user.api.CreateUserRequest
import br.com.rubensrodrigues.todolist.user.domain.User
import br.com.rubensrodrigues.todolist.user.domain.UserAlreadyExistsException
import br.com.rubensrodrigues.todolist.user.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository
) {

    private val passwordEncoder = BCryptPasswordEncoder()

    fun createUser(request: CreateUserRequest): User {
        if (repository.findByUsername(request.username) != null) {
            throw UserAlreadyExistsException(request.username)
        }

        val hash = passwordEncoder.encode(request.password)

        val user = User(
            username = request.username,
            passwordHash = hash
        )

        return repository.save(user)
    }
}