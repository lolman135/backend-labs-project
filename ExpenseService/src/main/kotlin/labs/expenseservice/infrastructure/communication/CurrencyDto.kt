package labs.expenseservice.infrastructure.communication

import java.util.UUID

data class CurrencyDto(
    val id: UUID,
    val code: String,
    val name: String,
    val symbol: String
)