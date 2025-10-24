package labs.userservice.application.usecase.user

import labs.userservice.application.usecase.UseCase
import labs.userservice.domain.UserRepository
import java.util.UUID

class DeleteUserByIdUseCase(private val userRepository: UserRepository) : UseCase<UUID, Unit> {

    override fun execute(input: UUID) {
        userRepository.deleteById(input)
    }
}