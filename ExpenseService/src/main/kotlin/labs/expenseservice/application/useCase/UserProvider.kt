package labs.expenseservice.application.useCase

import labs.expenseservice.application.useCase.userExternal.UserExternalInfo
import java.util.UUID

interface UserProvider {
    fun userExistsById(userId: UUID): Boolean
    fun getUserInfoById(userId: UUID): UserExternalInfo
}