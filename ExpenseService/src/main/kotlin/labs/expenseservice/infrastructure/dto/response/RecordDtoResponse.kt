package labs.expenseservice.infrastructure.dto.response

import java.util.UUID

data class RecordDtoResponse(
    val id: UUID,
    val category: String,
    val user: UserSubDto,
    val totalCost: Int
)
