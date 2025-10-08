package labs.expenseservice.infrastructure.dto.request

import jakarta.validation.constraints.Min
import java.util.UUID

data class RecordDtoRequest (
    val categoryId: UUID,
    val userId: UUID,
    @field:Min(value = 1, message = "Total cost must be greater than 0")
    val totalCost: Int
)