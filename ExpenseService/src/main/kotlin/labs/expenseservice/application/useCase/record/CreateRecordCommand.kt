package labs.expenseservice.application.useCase.record

import java.util.UUID

data class CreateRecordCommand(
    val categoryId: UUID,
    val userId: UUID,
    val totalCost: Int,
    val currencyId: UUID
)