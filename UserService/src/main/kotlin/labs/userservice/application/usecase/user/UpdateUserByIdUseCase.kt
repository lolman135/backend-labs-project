package labs.userservice.application.usecase.user

import labs.userservice.application.exception.EntityAlreadyExistsException
import labs.userservice.application.exception.EntityNotFoundException
import labs.userservice.application.usecase.CurrencyProvider
import labs.userservice.application.usecase.UseCase
import labs.userservice.domain.RoleRepository
import labs.userservice.domain.User
import labs.userservice.domain.UserRepository

class UpdateUserByIdUseCase(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val currencyProvider: CurrencyProvider
) : UseCase<UpdateUserCommand, User> {
    override fun execute(input: UpdateUserCommand): User {

        val existingUser = userRepository.findById(input.id!!)
            ?: throw EntityNotFoundException("User with id=${input.id} not found")

        val newName = input.name ?: existingUser.name
        val newEmail = input.email ?: existingUser.email

        if ((existingUser.name != newName || existingUser.email != newEmail)
            && userRepository.existsByNameOrEmail(newName, newEmail))
            throw EntityAlreadyExistsException("This user already exists")

        var updatedUser = existingUser
        input.name?.let { updatedUser = updatedUser.rename(it) }
        input.email?.let { updatedUser = updatedUser.changeEmail(it) }
        input.password?.let { updatedUser = updatedUser.changePassword(it) }
        input.rolesId?.forEach {
            if (!roleRepository.existsById(it))
                throw EntityNotFoundException("Role with id=$it not found")
            updatedUser = updatedUser.addRole(it)
        }

        input.defaultCurrencyId?.let {
            if (!currencyProvider.currencyExistsById(it))
                throw EntityNotFoundException("Currency with id=$it not found")
            updatedUser = updatedUser.changeCurrency(it)
        }

        return userRepository.save(updatedUser)
    }
}