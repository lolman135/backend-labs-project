package labs.expenseservice.application.useCase.external

import java.util.UUID

data class UserExternalInfo(
    val id: UUID,
    val name: String
)
