package labs.expenseservice.infrastructure.dto.response

import java.time.LocalDateTime
import java.util.UUID

data class RecordDtoResponse(
    val id: UUID,
    val category: String,
    val creationTime: LocalDateTime,
    val user: UserSubDto,
    val totalCost: Int,
    val currency: CurrencySubDto
)
