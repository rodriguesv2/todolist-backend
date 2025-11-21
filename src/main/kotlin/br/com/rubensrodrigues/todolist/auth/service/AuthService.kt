package br.com.rubensrodrigues.todolist.auth.service

import br.com.rubensrodrigues.todolist.auth.api.AuthRequest
import br.com.rubensrodrigues.todolist.auth.domain.InvalidCredentialsException
import br.com.rubensrodrigues.todolist.security.jwt.JwtService
import br.com.rubensrodrigues.todolist.user.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val jwtService: JwtService
) {

    private val passwordEncoder = BCryptPasswordEncoder()

    fun login(request: AuthRequest): String {
        val user = userRepository.findByUsername(request.username)
            ?: throw InvalidCredentialsException()

        val passwordMatches = passwordEncoder.matches(request.password, user.passwordHash)
        if (!passwordMatches) {
            throw InvalidCredentialsException()
        }

        return jwtService.generateToken(user.username)
    }
}