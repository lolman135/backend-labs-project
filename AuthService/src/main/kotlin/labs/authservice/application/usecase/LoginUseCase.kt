package labs.authservice.application.usecase

import labs.authservice.application.JwtProvider
import labs.authservice.application.exception.EntityNotFoundException
import labs.authservice.application.exception.InvalidCredentialsException
import labs.authservice.domain.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder

class LoginUseCase(
    private val userRepository: UserRepository,
    private val authenticationManager: AuthenticationManager,
    private val jwtProvider: JwtProvider
) : UseCase<LoginUserCommand, String>{

    override fun execute(input: LoginUserCommand): String {
        try{
            val authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(input.email, input.password)
            )
            SecurityContextHolder.getContext().authentication = authentication
            val authorizedUser = userRepository.findUserByEmail(input.email)
                ?: throw EntityNotFoundException("User with that email not found")

            return jwtProvider.generateToken(authorizedUser.id)
        } catch (ex: Exception){
            throw InvalidCredentialsException("Invalid username or password")
        }
    }
}