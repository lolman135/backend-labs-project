package labs.authservice.application.usecase

import labs.authservice.application.JwtProvider
import labs.authservice.application.exception.EntityAlreadyExistsException
import labs.authservice.application.exception.EntityNotFoundException
import labs.authservice.domain.RoleRepository
import labs.authservice.domain.User
import labs.authservice.domain.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.UUID

class RegisterUseCase(
    private val passwordEncoder: PasswordEncoder,
    private val jwtProvider: JwtProvider,
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository
) : UseCase<RegisterUserCommand, String>{

    override fun execute(input: RegisterUserCommand): String {

        if (userRepository.existsByNameOrEmail(input.name, input.email))
            throw EntityAlreadyExistsException("User with this data already exists")

        val user = User(
            id = UUID.randomUUID(),
            name = input.name,
            email = input.email,
            password = passwordEncoder.encode(input.password),
            roles = listOf(
                roleRepository.getDefaultRole()
                    ?: throw EntityNotFoundException("There is no default role")
            )
        )

        val registeredUser = userRepository.save(user)
        return jwtProvider.generateToken(registeredUser.id)
    }
}