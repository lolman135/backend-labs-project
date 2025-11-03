package labs.expenseservice.infrastructure.communication

import java.util.UUID

data class CurrencySubDto(
    val id: UUID,
    val code: String
)