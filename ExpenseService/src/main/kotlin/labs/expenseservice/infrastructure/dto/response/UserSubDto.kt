package labs.expenseservice.infrastructure.dto.response

import java.util.UUID

data class UserSubDto(
    val id: UUID,
    val name: String
)