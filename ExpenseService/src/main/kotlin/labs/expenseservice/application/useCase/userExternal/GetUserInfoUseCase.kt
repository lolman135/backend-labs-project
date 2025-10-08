package labs.expenseservice.application.useCase.userExternal

import labs.expenseservice.application.useCase.UseCase
import labs.expenseservice.application.useCase.UserProvider
import java.util.UUID

class GetUserInfoUseCase(private val userProvider: UserProvider) : UseCase<UUID, UserExternalInfo> {

    override fun execute(command: UUID) = userProvider.getUserInfoById(command)
}