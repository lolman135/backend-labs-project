package labs.expenseservice.infrastructure.dto.response

import java.util.UUID

data class CurrencySubDto(
    val id: UUID,
    val code: String
)