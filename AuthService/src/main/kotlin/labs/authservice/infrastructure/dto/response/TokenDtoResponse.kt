package labs.authservice.infrastructure.dto.response

data class TokenDtoResponse(
    val type: String = "Bearer",
    val token: String
)
