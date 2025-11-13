package labs.authservice.persistence

import labs.authservice.domain.User
import labs.authservice.domain.UserRepository
import labs.authservice.infrastructure.mapper.UserMapper
import labs.authservice.infrastructure.mapper.UserMapperHelper
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class UserRepositoryImpl(
    private val jpaUserRepository: JpaUserRepository,
    private val userMapper: UserMapper,
    private val userMapperHelper: UserMapperHelper
) : UserRepository{

    override fun save(user: User): User {
        val entity = jpaUserRepository.save(userMapper.toEntity(user, userMapperHelper))
        return userMapper.toDomain(entity, userMapperHelper)
    }

    override fun findById(id: UUID): User? = jpaUserRepository.findById(id).map {
        userMapper.toDomain(it, userMapperHelper)
    }.orElse(null)

    override fun existsById(id: UUID) = jpaUserRepository.existsById(id)


    override fun existsByNameOrEmail(name: String, email: String) =
        jpaUserRepository.existsUserEntityByNameOrEmail(name = name, email = email)

    override fun findUserByEmail(email: String): User? = jpaUserRepository.findUserEntityByEmail(email).map {
        userMapper.toDomain(it, userMapperHelper)
    }.orElse(null)
}