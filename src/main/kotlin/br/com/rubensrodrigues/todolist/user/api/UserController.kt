package br.com.rubensrodrigues.todolist.user.api

import br.com.rubensrodrigues.todolist.user.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: CreateUserRequest): UserResponse {
        val user = userService.createUser(request)

        return UserResponse(
            id = user.id,
            username = user.username,
            createdAt = user.createdAt.toString()
        )
    }
}