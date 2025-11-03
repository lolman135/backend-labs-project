package labs.expenseservice.application.useCase.external

import labs.expenseservice.application.exception.EntityNotFoundException
import labs.expenseservice.application.useCase.UseCase
import labs.expenseservice.application.useCase.UserProvider
import java.util.UUID

class GetUserInfoUseCase(private val userProvider: UserProvider) : UseCase<UUID, UserExternalInfo> {

    override fun execute(command: UUID) = try {
            userProvider.getUserInfoById(command)
        } catch (ex: IllegalStateException){
            throw EntityNotFoundException(ex.message!!)
        }

}