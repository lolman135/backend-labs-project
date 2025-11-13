package labs.authservice.application.usecase

data class LoginUserCommand(
    val email: String,
    val password: String
)