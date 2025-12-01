package br.com.rubensrodrigues.todolist.board.service

import br.com.rubensrodrigues.todolist.board.api.CreateBoardRequest
import br.com.rubensrodrigues.todolist.board.domain.Board
import br.com.rubensrodrigues.todolist.board.repository.BoardRepository
import br.com.rubensrodrigues.todolist.todo.domain.UserNotFoundException
import br.com.rubensrodrigues.todolist.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class BoardService(
    private val boardRepository: BoardRepository,
    private val userRepository: UserRepository,
) {
    fun createBoard(
        request: CreateBoardRequest,
        username: String
    ): Board {
        val user = userRepository.findByUsername(username)
            ?: throw UserNotFoundException(username)

        val board = Board(
            name = request.name,
            description = request.description,
            user = user
        )

       return boardRepository.save(board)
    }

    fun listBoardsForUser(username: String): List<Board> {
        val user = userRepository.findByUsername(username)
            ?: throw UserNotFoundException(username)

        return boardRepository.findAllByUserId(user.id)
    }
}