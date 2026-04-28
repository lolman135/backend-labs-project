package labs.authservice.persistence

import labs.authservice.domain.User
import labs.authservice.infrastructure.mapper.UserMapper
import labs.authservice.infrastructure.mapper.UserMapperHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import java.util.*

class UserRepositoryImplTest {

    private val jpaUserRepository: JpaUserRepository = mock()
    private val userMapper: UserMapper = mock()
    private val userMapperHelper: UserMapperHelper = mock()
    private val repository = UserRepositoryImpl(jpaUserRepository, userMapper, userMapperHelper)

    @Test
    fun `save should map and return domain user`() {
        // GIVEN
        val userId = UUID.randomUUID()
        val domainUser = User(userId, "Admin", "admin@test.com", "hash")
        val entity = UserEntity(id = userId, name = "Admin", email = "admin@test.com", password = "hash")

        whenever(userMapper.toEntity(domainUser, userMapperHelper)).thenReturn(entity)
        whenever(jpaUserRepository.save(entity)).thenReturn(entity)
        whenever(userMapper.toDomain(entity, userMapperHelper)).thenReturn(domainUser)

        // WHEN
        val result = repository.save(domainUser)

        // THEN
        assertThat(result.id).isEqualTo(userId)
        verify(jpaUserRepository).save(entity)
        verify(userMapper).toEntity(domainUser, userMapperHelper)
    }

    @Test
    fun `findUserByEmail should return mapped user if exists`() {
        // GIVEN
        val email = "login@test.com"
        val entity = UserEntity(id = UUID.randomUUID(), name = "User", email = email, password = "xxx")
        val domain = User(entity.id!!, "User", email, "xxx")

        whenever(jpaUserRepository.findUserEntityByEmail(email)).thenReturn(Optional.of(entity))
        whenever(userMapper.toDomain(entity, userMapperHelper)).thenReturn(domain)

        // WHEN
        val result = repository.findUserByEmail(email)

        // THEN
        assertThat(result?.email).isEqualTo(email)
        verify(jpaUserRepository).findUserEntityByEmail(email)
    }

    @Test
    fun `existsByNameOrEmail should return repository result`() {
        whenever(jpaUserRepository.existsUserEntityByNameOrEmail("name", "email")).thenReturn(true)

        val exists = repository.existsByNameOrEmail("name", "email")

        assertThat(exists).isTrue()
        verify(jpaUserRepository).existsUserEntityByNameOrEmail("name", "email")
    }
}