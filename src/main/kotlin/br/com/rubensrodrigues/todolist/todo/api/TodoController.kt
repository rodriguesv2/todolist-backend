package br.com.rubensrodrigues.todolist.todo.api

import br.com.rubensrodrigues.todolist.security.CurrentUserProvider
import br.com.rubensrodrigues.todolist.todo.domain.Todo
import br.com.rubensrodrigues.todolist.todo.domain.TodoStatus
import br.com.rubensrodrigues.todolist.todo.service.TodoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/todos")
class TodoController(
    private val todoService: TodoService,
    private val currentUserProvider: CurrentUserProvider
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: CreateTodoRequest): TodoResponse {
        val username = currentUserProvider.getCurrentUsername()
        val todo = todoService.createTodo(request, username)
        return todo.toResponse()
    }

    @GetMapping
    fun list(
        @RequestParam(required = false) status: TodoStatus?
    ): List<TodoResponse> {
        val username = currentUserProvider.getCurrentUsername()
        val todos = todoService.listTodosForUser(username, status)
        return todos.map { it.toResponse() }
    }

    private fun Todo.toResponse() = TodoResponse(
        id = this.id,
        title = this.title,
        description = this.description,
        status = this.status,
        ownerId = this.owner.id,
        createdAt = this.createdAt.toString(),
        completedAt = this.completedAt?.toString()
    )
}