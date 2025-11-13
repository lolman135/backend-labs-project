package labs.userservice.application.usecase.user

import java.util.UUID

data class CreateUserCommand(
    val name: String,
    val email: String,
    val password: String,
    val defaultCurrencyId: UUID?
)