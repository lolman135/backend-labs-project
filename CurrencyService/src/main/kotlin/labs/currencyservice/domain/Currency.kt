package labs.currencyservice.domain

import java.util.UUID

data class Currency(
    val id: UUID,
    val code: String,
    val name: String,
    val symbol: String
)
