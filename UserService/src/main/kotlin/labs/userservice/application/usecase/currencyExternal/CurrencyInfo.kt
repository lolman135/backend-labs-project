package labs.userservice.application.usecase.currencyExternal

import java.util.UUID

data class CurrencyInfo(
    val id: UUID,
    val code: String,
)