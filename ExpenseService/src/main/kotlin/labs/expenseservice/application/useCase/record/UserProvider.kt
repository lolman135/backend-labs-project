package labs.expenseservice.application.useCase.record

import java.util.UUID

interface UserProvider {
    fun userExistsById(userId: UUID): Boolean
}