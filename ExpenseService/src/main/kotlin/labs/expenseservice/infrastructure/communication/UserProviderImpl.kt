package labs.expenseservice.infrastructure.communication

import labs.expenseservice.application.useCase.UserProvider
import labs.expenseservice.application.useCase.userExternal.UserExternalInfo
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserProviderImpl(
    private val userRestClient: UserRestClient
) : UserProvider {

    override fun userExistsById(userId: UUID) =
        try {
            userRestClient.getUserById(userId)
            true
        } catch (_: IllegalStateException){
            false
        }

    override fun getUserInfoById(userId: UUID): UserExternalInfo {
        val externalUser = userRestClient.getUserById(userId)
        return UserExternalInfo(externalUser.id, externalUser.name)
    }
}