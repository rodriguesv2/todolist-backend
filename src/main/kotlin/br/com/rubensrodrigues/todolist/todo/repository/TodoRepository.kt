package br.com.rubensrodrigues.todolist.todo.repository

import br.com.rubensrodrigues.todolist.todo.domain.Todo
import br.com.rubensrodrigues.todolist.todo.domain.TodoStatus
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {
    fun findAllByBoardId(boardId: Long): List<Todo>
    fun findAllByBoardIdAndStatus(boardId: Long, status: TodoStatus): List<Todo>
}