package labs.userservice.application.usecase.role

import labs.userservice.common.toRoleFormat
import labs.userservice.domain.RoleRepository
import org.assertj.core.api.Assertions.assertThat
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.argThat
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test

class UpdateRoleByIdUseCaseTest {
    private val roleRepository: RoleRepository = mock()
    private val useCase = UpdateRoleByIdUseCase(roleRepository)

    @Test
    fun `should update role name successfully`() {
        val roleId = java.util.UUID.randomUUID()
        val existingRole = labs.userservice.domain.Role(roleId, "ROLE_OLD")
        val command = UpsertRoleCommand("new_name")
        val formattedNewName = "new_name".toRoleFormat()

        whenever(roleRepository.findById(roleId)).thenReturn(existingRole)
        whenever(roleRepository.existsByName(formattedNewName)).thenReturn(false)
        whenever(roleRepository.save(any())).thenAnswer { it.arguments[0] }

        val result = useCase.execute(roleId to command)

        assertThat(result.name).isEqualTo(formattedNewName)
        verify(roleRepository).save(argThat { name == formattedNewName })
    }
}