package labs.userservice.application.usecase.user

import labs.userservice.application.usecase.UseCase
import labs.userservice.domain.User
import labs.userservice.domain.UserRepository

class FindAllUsersUseCase(private val userRepository: UserRepository) : UseCase<Unit, List<User>> {

    override fun execute(input: Unit) = userRepository.findAll();
}