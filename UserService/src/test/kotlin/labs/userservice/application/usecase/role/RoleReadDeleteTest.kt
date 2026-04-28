package labs.userservice.application.usecase.role

import labs.userservice.domain.Role
import labs.userservice.domain.RoleRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import java.util.UUID

class RoleReadDeleteTest {

    private val roleRepository: RoleRepository = mock()

    @Test
    fun `FindAllRolesUseCase should return all roles`() {
        val useCase = FindAllRolesUseCase(roleRepository)
        val roles = listOf(Role(UUID.randomUUID(), "A"), Role(UUID.randomUUID(), "B"))
        whenever(roleRepository.findAll()).thenReturn(roles)

        val result = useCase.execute(Unit)

        assertThat(result).hasSize(2)
        verify(roleRepository).findAll()
    }

    @Test
    fun `DeleteRoleByIdUseCase should call delete`() {
        val useCase = DeleteRoleByIdUseCase(roleRepository)
        val id = UUID.randomUUID()

        useCase.execute(id)

        verify(roleRepository).deleteById(id)
    }
}