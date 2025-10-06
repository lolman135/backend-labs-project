package labs.userservice.application.usecase

import labs.userservice.domain.User
import labs.userservice.domain.UserRepository

class FindAllUsersUseCase(private val userRepository: UserRepository) : UseCase<Unit, List<User>> {

    override fun execute(input: Unit) = userRepository.findAll();
}