package br.com.rubensrodrigues.todolist.security.jwt

import br.com.rubensrodrigues.todolist.config.JwtProperties
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.util.Date

@Service
class JwtService(
    private val jwtProperties: JwtProperties
) {

    private val key = Keys.hmacShaKeyFor(jwtProperties.secret.toByteArray(StandardCharsets.UTF_8))

    fun generateToken(username: String): String {
        val now = Instant.now()
        val expiration = now.plusSeconds(jwtProperties.expirationSeconds)

        return Jwts.builder()
            .subject(username)
            .issuedAt(Date.from(now))
            .expiration(Date.from(expiration))
            .signWith(key)
            .compact()
    }

    fun extractUsername(token: String): String? {
        val claims = Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)

        return claims.payload.subject
    }

    fun isValid(token: String): Boolean {
        return try {
            val claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)

            val exp = claims.payload.expiration.toInstant()
            exp.isAfter(Instant.now())
        } catch (ex: Exception) {
            false
        }
    }
}