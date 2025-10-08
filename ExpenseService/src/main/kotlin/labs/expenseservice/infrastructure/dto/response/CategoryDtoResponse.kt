package labs.expenseservice.infrastructure.dto.response

import java.util.UUID

data class CategoryDtoResponse(
    val id: UUID,
    val name: String
)