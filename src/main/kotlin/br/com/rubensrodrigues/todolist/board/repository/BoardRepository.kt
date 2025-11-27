package br.com.rubensrodrigues.todolist.board.repository

import br.com.rubensrodrigues.todolist.board.domain.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository: JpaRepository<Board, Long> {
    fun findAllByOwnerId(ownerId: Long): List<Board>
}