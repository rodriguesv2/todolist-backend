package br.com.rubensrodrigues.todolist.todo.api

import br.com.rubensrodrigues.todolist.security.CurrentUserProvider
import br.com.rubensrodrigues.todolist.todo.domain.Todo
import br.com.rubensrodrigues.todolist.todo.domain.TodoStatus
import br.com.rubensrodrigues.todolist.todo.service.TodoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todos")
class TodoController(
    private val todoService: TodoService,
    private val currentUserProvider: CurrentUserProvider
) {

    @PostMapping("/{boardId}")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @PathVariable boardId: Long,
        @RequestBody @Valid request: CreateTodoRequest
    ): TodoResponse {
        val username = currentUserProvider.getCurrentUsername()
        val todo = todoService.createTodo(
            request = request,
            boardId = boardId,
            username = username,
        )
        return todo.toResponse()
    }

    @GetMapping("/{boardId}")
    fun list(
        @PathVariable boardId: Long,
        @RequestParam(required = false) status: TodoStatus?
    ): List<TodoResponse> {
        val username = currentUserProvider.getCurrentUsername()
        val todos = todoService.listTodosForBoard(
            boardId = boardId,
            username = username,
            status = status
        )
        return todos.map { it.toResponse() }
    }

    private fun Todo.toResponse() = TodoResponse(
        id = this.id,
        title = this.title,
        description = this.description,
        status = this.status,
        boardId = this.board.id,
        boardName = this.board.name,
        createdAt = this.createdAt.toString(),
        completedAt = this.completedAt?.toString()
    )
}