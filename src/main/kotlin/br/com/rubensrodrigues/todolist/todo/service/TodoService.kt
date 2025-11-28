package br.com.rubensrodrigues.todolist.todo.service

import br.com.rubensrodrigues.todolist.board.repository.BoardRepository
import br.com.rubensrodrigues.todolist.todo.api.CreateTodoRequest
import br.com.rubensrodrigues.todolist.todo.domain.Todo
import br.com.rubensrodrigues.todolist.todo.domain.TodoStatus
import br.com.rubensrodrigues.todolist.todo.domain.UserNotFoundException
import br.com.rubensrodrigues.todolist.todo.repository.TodoRepository
import br.com.rubensrodrigues.todolist.user.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class TodoService(
    private val todoRepository: TodoRepository,
    private val userRepository: UserRepository,
    private val boardRepository: BoardRepository,
) {

    fun createTodo(
        request: CreateTodoRequest,
        boardId: Long,
        username: String,
    ): Todo {
        val owner = userRepository.findByUsername(username)
            ?: throw UserNotFoundException(username)

        val board = boardRepository.findById(boardId).orElseThrow {
            RuntimeException("Board not found with id $boardId")
        }

        if (board.user.id != owner.id) {
            throw RuntimeException("User is not the owner of the board")
        }

        val now = Instant.now()

        val todo = Todo(
            title = request.title,
            description = request.description,
            board = board,
            status = TodoStatus.PENDING,
            createdAt = now,
            updatedAt = now,
            completedAt = null
        )

        return todoRepository.save(todo)
    }

    fun listTodosForUser(username: String, status: TodoStatus?): List<Todo> {
        val owner = userRepository.findByUsername(username)
            ?: throw UserNotFoundException(username)

        return if (status != null) {
            todoRepository.findAllByOwnerIdAndStatus(owner.id, status)
        } else {
            todoRepository.findAllByOwnerId(owner.id)
        }
    }
}