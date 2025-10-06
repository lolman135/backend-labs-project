package labs.userservice.application.usecase

import labs.userservice.application.exception.EntityNotFoundException
import labs.userservice.domain.User
import labs.userservice.domain.UserRepository

class UpdateUserByIdUseCase(private val userRepository: UserRepository) : UseCase<UpdateUserCommand, User> {
    override fun execute(input: UpdateUserCommand): User {
        val existingUser = userRepository.findById(input.id!!)
            ?: throw EntityNotFoundException("User with id=${input.id} not found")

        var updatedUser = existingUser
        input.name?.let { updatedUser = updatedUser.rename(it) }
        input.email?.let { updatedUser = updatedUser.changeEmail(it) }
        input.password?.let { updatedUser = updatedUser.changePassword(it) }
        input.roles?.forEach { updatedUser = updatedUser.addRole(it) }

        return userRepository.save(updatedUser)
    }
}