package labs.userservice.infrastructure.dto.response

import java.util.UUID

data class UserDtoResponse(
    val id: UUID,
    val name: String,
    val email: String,
    val roles: List<String>,
    val defaultCurrency: CurrencySubDto
)