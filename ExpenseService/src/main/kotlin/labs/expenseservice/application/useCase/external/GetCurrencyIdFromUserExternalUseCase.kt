package labs.expenseservice.application.useCase.external

import labs.expenseservice.application.exception.EntityNotFoundException
import labs.expenseservice.application.useCase.UseCase
import labs.expenseservice.application.useCase.UserProvider
import java.util.UUID

class GetCurrencyIdFromUserExternalUseCase(private val userProvider: UserProvider) : UseCase<UUID, UUID> {
    override fun execute(input: UUID) = try {
        userProvider.getCurrencyIdByUserId(input)
    } catch (ex: IllegalStateException){
        throw EntityNotFoundException(ex.message!!)
    }
}