package labs.userservice.application.usecase

import labs.userservice.application.exception.EntityAlreadyExistsException
import labs.userservice.domain.Role
import labs.userservice.domain.User
import labs.userservice.domain.UserRepository
import java.util.UUID

class CreateUserUseCase(private val userRepository: UserRepository) : UseCase<CreateUserCommand, User>{

    override fun execute(input: CreateUserCommand): User {
        if (userRepository.existsByNameOrEmail(input.name, input.email))
            throw EntityAlreadyExistsException("This user is already exists")

        val user = User(
            id = UUID.randomUUID(),
            name = input.name,
            email = input.email,
            password = input.password,
            roles = listOf(Role.ROLE_USER)
        )
        return userRepository.save(user)
    }
}