package br.com.rubensrodrigues.todolist.todo.api

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
    private val todoService: TodoService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: CreateTodoRequest): TodoResponse {
        val todo = todoService.createTodo(request)
        return todo.toResponse()
    }

    @GetMapping
    fun listByUser(
        @RequestParam userId: Long,
        @RequestParam(required = false) status: TodoStatus?
    ): List<TodoResponse> {
        val todos = todoService.listTodosByUser(userId, status)
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