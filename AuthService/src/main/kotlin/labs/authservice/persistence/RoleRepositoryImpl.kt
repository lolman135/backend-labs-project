package labs.authservice.persistence

import labs.authservice.domain.Role
import labs.authservice.domain.RoleRepository
import labs.authservice.infrastructure.mapper.RoleMapper
import org.springframework.stereotype.Repository

@Repository
class RoleRepositoryImpl(
    private val jpaRoleRepository: JpaRoleRepository,
    private val roleMapper: RoleMapper
) : RoleRepository{

    override fun getDefaultRole(): Role? {
        val optionalEntity = jpaRoleRepository.findRoleEntityByName("ROLE_USER")
        return optionalEntity.map { roleMapper.toDomain(it) }.orElse(null)
    }
}