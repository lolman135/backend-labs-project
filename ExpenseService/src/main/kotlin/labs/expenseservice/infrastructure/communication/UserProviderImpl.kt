package labs.expenseservice.infrastructure.communication

import labs.expenseservice.application.exception.EntityNotFoundException
import labs.expenseservice.application.useCase.record.UserProvider
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserProviderImpl(
    private val userRestClient: UserRestClient
) : UserProvider {

    override fun userExistsById(userId: UUID): Boolean {
        try {
            userRestClient.getUserById(userId)
            return true
        } catch (ex: IllegalStateException){
            return false
        }
    }
}