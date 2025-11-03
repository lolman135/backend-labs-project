package labs.expenseservice.application.useCase.external

import java.util.UUID

data class CurrencyInfo(
    val id: UUID,
    val code: String,
)