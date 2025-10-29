package labs.userservice.application.usecase.user

import labs.userservice.application.exception.EntityAlreadyExistsException
import labs.userservice.application.exception.EntityNotFoundException
import labs.userservice.application.usecase.CurrencyProvider
import labs.userservice.application.usecase.UseCase
import labs.userservice.domain.RoleRepository
import labs.userservice.domain.User
import labs.userservice.domain.UserRepository
import java.util.UUID

class CreateUserUseCase(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val currencyProvider: CurrencyProvider
) : UseCase<CreateUserCommand, User> {

    override fun execute(input: CreateUserCommand): User {
        if (userRepository.existsByNameOrEmail(input.name, input.email))
            throw EntityAlreadyExistsException("This user is already exists")

        if (!currencyProvider.currencyExistsById(input.defaultCurrencyId))
            throw EntityNotFoundException("Currency with id=${input.defaultCurrencyId} not found")

        val defaultRoleId = roleRepository.getDefaultRole()?.id
            ?: throw EntityNotFoundException("There is no default role")

        val user = User(
            id = UUID.randomUUID(),
            name = input.name,
            email = input.email,
            password = input.password,
            roleIds = listOf(defaultRoleId),
            defaultCurrencyId = input.defaultCurrencyId
        )

        println("user's roles :" + user.roleIds)
        return userRepository.save(user)
    }
}