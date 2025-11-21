package br.com.rubensrodrigues.todolist.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "app.security.jwt")
data class JwtProperties @ConstructorBinding constructor(
    val secret: String,
    val expirationSeconds: Long
)