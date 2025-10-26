package labs.currencyservice.infrastructure.dto.outbound

import java.util.UUID

data class CurrencyDtoOutbound(
    val id: UUID,
    val code: String,
    val name: String,
    val symbol: String
)
