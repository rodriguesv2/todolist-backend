package br.com.rubensrodrigues.todolist.todo.domain

import br.com.rubensrodrigues.todolist.board.domain.Board
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "todos")
class Todo(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = true, length = 2000)
    val description: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: TodoStatus = TodoStatus.PENDING,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    val board: Board,

    @Column(nullable = false)
    val createdAt: Instant = Instant.now(),

    @Column(nullable = false)
    val updatedAt: Instant = Instant.now(),

    @Column(nullable = true)
    val completedAt: Instant? = null
)