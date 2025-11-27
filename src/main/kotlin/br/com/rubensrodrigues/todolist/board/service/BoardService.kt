package br.com.rubensrodrigues.todolist.board.service

import br.com.rubensrodrigues.todolist.board.api.CreateBoardRequest
import br.com.rubensrodrigues.todolist.board.repository.BoardRepository
import org.springframework.stereotype.Service

@Service
class BoardService(
    private val boardRepository: BoardRepository
) {
    fun createBoard(
        request: CreateBoardRequest,
        username: String
    ) {

    }
}