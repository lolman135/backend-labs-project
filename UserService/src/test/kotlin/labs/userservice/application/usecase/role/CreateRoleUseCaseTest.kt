package labs.userservice.application.usecase.role

import labs.userservice.application.exception.EntityAlreadyExistsException
import labs.userservice.common.toRoleFormat
import labs.userservice.domain.RoleRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*

class CreateRoleUseCaseTest {
    private val roleRepository: RoleRepository = mock()
    private val useCase = CreateRoleUseCase(roleRepository)

    @Test
    fun `should create role successfully`() {
        val command = UpsertRoleCommand("admin")
        val formattedName = "admin".toRoleFormat()

        whenever(roleRepository.existsByName(formattedName)).thenReturn(false)
        whenever(roleRepository.save(any())).thenAnswer { it.arguments[0] }

        val result = useCase.execute(command)

        assertThat(result.name).isEqualTo(formattedName)
        verify(roleRepository).existsByName(formattedName)
        verify(roleRepository).save(any())
    }

    @Test
    fun `should throw exception when role already exists`() {
        val command = UpsertRoleCommand("admin")
        whenever(roleRepository.existsByName(any())).thenReturn(true)

        assertThrows<EntityAlreadyExistsException> {
            useCase.execute(command)
        }

        verify(roleRepository, never()).save(any())
    }
}