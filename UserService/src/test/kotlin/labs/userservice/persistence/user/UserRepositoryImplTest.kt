package labs.userservice.persistence.user

import labs.userservice.domain.User
import labs.userservice.infrastructure.mapper.UserMapper
import labs.userservice.infrastructure.mapper.UserMapperHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import java.util.*

class UserRepositoryImplTest {

    private val userJpaRepository: UserJpaRepository = mock()
    private val userMapper: UserMapper = mock()
    private val userMapperHelper: UserMapperHelper = mock()
    private val repository = UserRepositoryImpl(userJpaRepository, userMapper, userMapperHelper)

    @Test
    fun `save should correctly use mapper with helper`() {
        // GIVEN
        val userId = UUID.randomUUID()
        val domainUser = User(userId, "Tester", "test@test.com", "hash", listOf(), null)

        val entity = UserEntity(
            id = userId,
            name = "Tester",
            email = "test@test.com",
            password = "hash",
            roles = mutableSetOf(),
            defaultCurrencyId = null
        )

        whenever(userMapper.toEntity(domainUser, userMapperHelper)).thenReturn(entity)
        whenever(userJpaRepository.save(entity)).thenReturn(entity)
        whenever(userMapper.toDomainFromEntity(entity)).thenReturn(domainUser)

        // WHEN
        val result = repository.save(domainUser)

        // THEN
        assertThat(result.email).isEqualTo("test@test.com")
        verify(userMapper).toEntity(domainUser, userMapperHelper)
        verify(userJpaRepository).save(any<UserEntity>())
    }

    @Test
    fun `existsByNameOrEmail should delegate to jpa repository`() {
        val name = "Alice"
        val email = "alice@example.com"
        whenever(userJpaRepository.existsUserEntityByNameOrEmail(name, email)).thenReturn(true)

        val exists = repository.existsByNameOrEmail(name, email)

        assertThat(exists).isTrue()
        verify(userJpaRepository).existsUserEntityByNameOrEmail(name, email)
    }

    @Test
    fun `findAll should map list of entities to domain users`() {
        val entity1 = UserEntity(UUID.randomUUID(), "U1", "e1", "p1", mutableSetOf(), null)
        val entity2 = UserEntity(UUID.randomUUID(), "U2", "e2", "p2", mutableSetOf(), null)

        whenever(userJpaRepository.findAll()).thenReturn(listOf(entity1, entity2))
        whenever(userMapper.toDomainFromEntity(any())).thenReturn(mock<User>())

        val result = repository.findAll()

        assertThat(result).hasSize(2)
        verify(userMapper, times(2)).toDomainFromEntity(any())
    }
}