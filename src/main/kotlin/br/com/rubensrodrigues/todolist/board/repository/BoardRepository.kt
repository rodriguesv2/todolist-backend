package br.com.rubensrodrigues.todolist.board.repository

import br.com.rubensrodrigues.todolist.board.domain.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository: JpaRepository<Board, Long> {
    fun findAllByUserId(userId: Long): List<Board>
}