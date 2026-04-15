package labs.userservice.application.usecase.user

import labs.userservice.application.exception.EntityAlreadyExistsException
import labs.userservice.application.exception.EntityNotFoundException
import labs.userservice.application.usecase.CurrencyProvider
import labs.userservice.domain.RoleRepository
import labs.userservice.domain.User
import labs.userservice.domain.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.util.UUID

class UpdateUserByIdUseCaseTest {

    private val userRepository: UserRepository = mock()
    private val roleRepository: RoleRepository = mock()
    private val currencyProvider: CurrencyProvider = mock()

    private val useCase = UpdateUserByIdUseCase(userRepository, roleRepository, currencyProvider)

    @Test
    fun `should update all user fields successfully`() {
        // GIVEN
        val userId = UUID.randomUUID()
        val roleId = UUID.randomUUID()
        val currencyId = UUID.randomUUID()
        val existingUser = User(userId, "Old", "old@mail.com", "pass", listOf(), null)

        val command = UpdateUserCommand(
            id = userId,
            name = "New Name",
            email = "new@mail.com",
            password = "newPassword",
            rolesId = listOf(roleId),
            defaultCurrencyId = currencyId
        )

        whenever(userRepository.findById(userId)).thenReturn(existingUser)
        whenever(userRepository.existsByNameOrEmail(any(), any())).thenReturn(false)
        whenever(roleRepository.existsById(roleId)).thenReturn(true)
        whenever(currencyProvider.currencyExistsById(currencyId)).thenReturn(true)
        whenever(userRepository.save(any())).thenAnswer { it.arguments[0] }

        // WHEN
        val result = useCase.execute(command)

        // THEN
        assertThat(result.name).isEqualTo("New Name")
        assertThat(result.email).isEqualTo("new@mail.com")
        assertThat(result.password).isEqualTo("newPassword")
        assertThat(result.roleIds).contains(roleId)
        assertThat(result.defaultCurrencyId).isEqualTo(currencyId)
        verify(userRepository).save(any())
    }

    @Test
    fun `should throw exception if email is already taken by another user`() {
        val userId = UUID.randomUUID()
        val existingUser = User(userId, "Ivan", "ivan@mail.com", "123", listOf(), null)
        val command = UpdateUserCommand(userId, name = "New", email = "taken@mail.com", null, null, null)

        whenever(userRepository.findById(userId)).thenReturn(existingUser)
        // Имитируем, что имя или почта уже заняты
        whenever(userRepository.existsByNameOrEmail("New", "taken@mail.com")).thenReturn(true)

        assertThrows<EntityAlreadyExistsException> {
            useCase.execute(command)
        }
    }

    @Test
    fun `should throw exception if role does not exist`() {
        val userId = UUID.randomUUID()
        val roleId = UUID.randomUUID()
        val existingUser = User(userId, "Ivan", "ivan@mail.com", "123", listOf(), null)
        val command = UpdateUserCommand(userId, null, null, null, listOf(roleId), null)

        whenever(userRepository.findById(userId)).thenReturn(existingUser)
        whenever(roleRepository.existsById(roleId)).thenReturn(false)

        assertThrows<EntityNotFoundException> {
            useCase.execute(command)
        }
    }
}