package br.com.rubensrodrigues.todolist.health

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

data class HealthResponse(
    val status: String,
    val application: String
)

@RestController
class HealthController {

    @GetMapping("/health")
    fun health(): HealthResponse {
        return HealthResponse(
            status = "OK",
            application = "todo-api"
        )
    }
}