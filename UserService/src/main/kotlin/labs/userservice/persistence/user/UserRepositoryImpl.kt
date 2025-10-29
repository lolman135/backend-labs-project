package labs.userservice.persistence.user

import labs.userservice.domain.User
import labs.userservice.domain.UserRepository
import labs.userservice.infrastructure.mapper.UserMapper
import labs.userservice.infrastructure.mapper.UserMapperHelper
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class UserRepositoryImpl(
    private val userJpaRepository: UserJpaRepository,
    private val userMapper: UserMapper,
    private val userMapperHelper: UserMapperHelper
) : UserRepository {

    override fun deleteById(id: UUID) {
        userJpaRepository.deleteById(id)
    }

    override fun save(user: User): User {
        val entity = userMapper.toEntity(user, userMapperHelper)
        return userMapper.toDomainFromEntity(userJpaRepository.save(entity))
    }

    override fun findAll() = userJpaRepository.findAll().map{ userMapper.toDomainFromEntity(it) }

    override fun findById(id: UUID): User? = userJpaRepository.findById(id).map {
        userMapper.toDomainFromEntity(it)
    }.orElse(null)

    override fun existsById(id: UUID) = userJpaRepository.existsById(id)

    override fun existsByNameOrEmail(name: String, email: String) =
        userJpaRepository.existsUserEntityByNameOrEmail(name, email)
}