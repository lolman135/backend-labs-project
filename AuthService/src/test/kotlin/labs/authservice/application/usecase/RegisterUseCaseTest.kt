package labs.authservice.application.usecase

import labs.authservice.application.JwtProvider
import labs.authservice.application.exception.EntityAlreadyExistsException
import labs.authservice.domain.Role
import labs.authservice.domain.RoleRepository
import labs.authservice.domain.User
import labs.authservice.domain.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.UUID

class RegisterUseCaseTest {

    private val passwordEncoder: PasswordEncoder = mock()
    private val jwtProvider: JwtProvider = mock()
    private val userRepository: UserRepository = mock()
    private val roleRepository: RoleRepository = mock()

    private val useCase = RegisterUseCase(passwordEncoder, jwtProvider, userRepository, roleRepository)

    @Test
    fun `should register user and return token`() {
        val command = RegisterUserCommand("NewUser", "new@mail.com", "raw_pass")
        val defaultRole = Role(UUID.randomUUID(), "ROLE_USER")

        whenever(userRepository.existsByNameOrEmail(any(), any())).thenReturn(false)
        whenever(roleRepository.getDefaultRole()).thenReturn(defaultRole)
        whenever(passwordEncoder.encode("raw_pass")).thenReturn("encoded_pass")
        whenever(userRepository.save(any())).thenAnswer { it.arguments[0] }
        whenever(jwtProvider.generateToken(any())).thenReturn("token_123")

        val result = useCase.execute(command)

        assertThat(result).isEqualTo("token_123")
        verify(userRepository).save(argThat {
            password == "encoded_pass" && name == "NewUser"
        })
    }

    @Test
    fun `should throw exception if user already exists`() {
        val command = RegisterUserCommand("Existing", "exists@mail.com", "pass")
        whenever(userRepository.existsByNameOrEmail(any(), any())).thenReturn(true)

        assertThrows<EntityAlreadyExistsException> {
            useCase.execute(command)
        }
    }
}