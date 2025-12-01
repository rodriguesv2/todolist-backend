package br.com.rubensrodrigues.todolist.board.api

import br.com.rubensrodrigues.todolist.board.domain.Board
import br.com.rubensrodrigues.todolist.board.service.BoardService
import br.com.rubensrodrigues.todolist.security.CurrentUserProvider
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/boards")
class BoardController(
    private val boardService: BoardService,
    private val currentUserProvider: CurrentUserProvider,
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: CreateBoardRequest): BoardResponse {
        val username = currentUserProvider.getCurrentUsername()
        val board = boardService.createBoard(
            request = request,
            username = username,
        )
        return board.toResponse()
    }

    @GetMapping
    fun list(): List<BoardResponse> {
        val username = currentUserProvider.getCurrentUsername()
        val boards = boardService.listBoardsForUser(username)
        return boards.map { it.toResponse() }
    }

    private fun Board.toResponse() = BoardResponse(
        id = this.id,
        name = this.name,
        description = this.description,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
    )
}