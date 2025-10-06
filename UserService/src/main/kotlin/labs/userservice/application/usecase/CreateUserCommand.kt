package labs.userservice.application.usecase

data class CreateUserCommand(
    val name: String,
    val email: String,
    val password: String
)