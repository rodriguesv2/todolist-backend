package br.com.rubensrodrigues.todolist.todo.service

import br.com.rubensrodrigues.todolist.todo.api.CreateTodoRequest
import br.com.rubensrodrigues.todolist.todo.domain.Todo
import br.com.rubensrodrigues.todolist.todo.domain.TodoStatus
import br.com.rubensrodrigues.todolist.todo.repository.TodoRepository
import br.com.rubensrodrigues.todolist.user.domain.User
import br.com.rubensrodrigues.todolist.user.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class TodoService(
    private val todoRepository: TodoRepository,
    private val userRepository: UserRepository
) {

    fun createTodo(request: CreateTodoRequest): Todo {
        val owner: User = userRepository.findById(request.userId)
            .orElseThrow { IllegalArgumentException("User with id=${request.userId} not found") }

        val todo = Todo(
            title = request.title,
            description = request.description,
            owner = owner,
            status = TodoStatus.PENDING,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
            completedAt = null
        )

        return todoRepository.save(todo)
    }

    fun listTodosByUser(userId: Long, status: TodoStatus?): List<Todo> {
        return if (status != null) {
            todoRepository.findAllByOwnerIdAndStatus(userId, status)
        } else {
            todoRepository.findAllByOwnerId(userId)
        }
    }
}