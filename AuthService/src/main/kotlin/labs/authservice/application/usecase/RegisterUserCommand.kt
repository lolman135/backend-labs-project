package labs.authservice.application.usecase

import jakarta.validation.constraints.Email

data class RegisterUserCommand(
    val name: String,
    val email: String,
    val password: String
)