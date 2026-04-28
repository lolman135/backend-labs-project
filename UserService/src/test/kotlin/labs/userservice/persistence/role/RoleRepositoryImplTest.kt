package labs.userservice.persistence.role

import labs.userservice.domain.Role
import labs.userservice.infrastructure.mapper.RoleMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import java.util.*

class RoleRepositoryImplTest {

    private val roleJpaRepository: RoleJpaRepository = mock()
    private val roleMapper: RoleMapper = mock()
    private val repository = RoleRepositoryImpl(roleJpaRepository, roleMapper)

    @Test
    fun `save should map domain to entity and return domain from saved entity`() {
        // GIVEN
        val roleId = UUID.randomUUID()
        val role = Role(roleId, "ROLE_ADMIN")
        val entity = RoleEntity(roleId, "ROLE_ADMIN")
        val savedEntity = RoleEntity(roleId, "ROLE_ADMIN")

        whenever(roleMapper.toEntity(role)).thenReturn(entity)
        whenever(roleJpaRepository.save(entity)).thenReturn(savedEntity)
        whenever(roleMapper.toDomainFromEntity(savedEntity)).thenReturn(role)

        // WHEN
        val result = repository.save(role)

        // THEN
        assertThat(result).isEqualTo(role)
        verify(roleJpaRepository).save(entity)
    }

    @Test
    fun `findById should return null if entity not found`() {
        val id = UUID.randomUUID()
        whenever(roleJpaRepository.findById(id)).thenReturn(Optional.empty())

        val result = repository.findById(id)

        assertThat(result).isNull()
    }

    @Test
    fun `deleteById should call jpa repository`() {
        val id = UUID.randomUUID()

        repository.deleteById(id)

        verify(roleJpaRepository).deleteById(id)
    }
}