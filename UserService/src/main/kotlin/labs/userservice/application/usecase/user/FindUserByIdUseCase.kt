package labs.userservice.application.usecase.user

import labs.userservice.application.exception.EntityNotFoundException
import labs.userservice.application.usecase.UseCase
import labs.userservice.domain.User
import labs.userservice.domain.UserRepository
import java.util.UUID

class FindUserByIdUseCase(private val userRepository: UserRepository) : UseCase<UUID, User> {

    override fun execute(input: UUID) = userRepository.findById(input)
        ?: throw EntityNotFoundException("User with id=$input not found")
}