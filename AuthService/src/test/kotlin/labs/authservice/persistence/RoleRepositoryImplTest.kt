package labs.authservice.persistence

import labs.authservice.domain.Role
import labs.authservice.infrastructure.mapper.RoleMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import java.util.*

class RoleRepositoryImplTest {

    private val jpaRoleRepository: JpaRoleRepository = mock()
    private val roleMapper: RoleMapper = mock()
    private val repository = RoleRepositoryImpl(jpaRoleRepository, roleMapper)

    @Test
    fun `getDefaultRole should return mapped domain role when exists`() {
        // GIVEN
        val entity = RoleEntity(id = UUID.randomUUID(), name = "ROLE_USER")
        val domain = Role(entity.id!!, "ROLE_USER")

        whenever(jpaRoleRepository.findRoleEntityByName("ROLE_USER")).thenReturn(Optional.of(entity))
        whenever(roleMapper.toDomain(entity)).thenReturn(domain)

        // WHEN
        val result = repository.getDefaultRole()

        // THEN
        assertThat(result).isNotNull
        assertThat(result?.name).isEqualTo("ROLE_USER")
        verify(jpaRoleRepository).findRoleEntityByName("ROLE_USER")
    }

    @Test
    fun `getDefaultRole should return null when not found`() {
        whenever(jpaRoleRepository.findRoleEntityByName(any())).thenReturn(Optional.empty())

        val result = repository.getDefaultRole()

        assertThat(result).isNull()
    }
}