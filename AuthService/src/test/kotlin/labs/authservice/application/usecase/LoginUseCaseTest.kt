package labs.authservice.application.usecase

import labs.authservice.application.JwtProvider
import labs.authservice.application.exception.InvalidCredentialsException
import labs.authservice.domain.User
import labs.authservice.domain.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import java.util.UUID

class LoginUseCaseTest {

    private val userRepository: UserRepository = mock()
    private val authenticationManager: AuthenticationManager = mock()
    private val jwtProvider: JwtProvider = mock()
    private val useCase = LoginUseCase(userRepository, authenticationManager, jwtProvider)

    @Test
    fun `should return token on successful login`() {
        val command = LoginUserCommand("test@mail.com", "password")
        val userId = UUID.randomUUID()
        val user = User(userId, "Ivan", "test@mail.com", "encoded_pass")
        val auth: Authentication = mock()

        whenever(authenticationManager.authenticate(any())).thenReturn(auth)
        whenever(userRepository.findUserByEmail(command.email)).thenReturn(user)
        whenever(jwtProvider.generateToken(userId)).thenReturn("mock_token")

        val result = useCase.execute(command)

        assertThat(result).isEqualTo("mock_token")
        verify(authenticationManager).authenticate(any())
    }

    @Test
    fun `should throw InvalidCredentialsException when authentication fails`() {
        val command = LoginUserCommand("wrong@mail.com", "pass")
        whenever(authenticationManager.authenticate(any())).thenThrow(BadCredentialsException("Fail"))

        assertThrows<InvalidCredentialsException> {
            useCase.execute(command)
        }
    }
}