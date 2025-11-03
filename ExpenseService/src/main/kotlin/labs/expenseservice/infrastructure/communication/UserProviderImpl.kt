package labs.expenseservice.infrastructure.communication

import labs.expenseservice.application.useCase.UserProvider
import labs.expenseservice.application.useCase.external.UserExternalInfo
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
        try {
            val externalUser = userRestClient.getUserById(userId)
            return UserExternalInfo(externalUser.id, externalUser.name)
        } catch (ex: IllegalStateException){
            throw IllegalStateException(ex.message)
        }
    }

    override fun getCurrencyIdByUserId(userId: UUID): UUID {
        try {
            return userRestClient.getUserById(userId).defaultCurrency.id
        } catch (ex: IllegalStateException){
            throw IllegalStateException(ex.message)
        }
    }
}