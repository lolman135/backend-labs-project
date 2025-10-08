package labs.expenseservice.application.useCase.record

import java.util.UUID

data class GetAllRecordsByIdsCommand(
    val categoryId: UUID?,
    val userId: UUID?
)
