package labs.authservice.infrastructure.mapper

import labs.authservice.domain.Role
import labs.authservice.persistence.RoleEntity
import org.springframework.stereotype.Component

@Component
class UserMapperHelper(
    private val roleMapper: RoleMapper
) {

    fun rolesToRoleEntities(roles: List<Role>) = roles
        .map { roleMapper.toEntity(it) }.toMutableSet()

    fun roleEntitiesToRoles(roleEntities: MutableSet<RoleEntity>) = roleEntities
        .map{ roleMapper.toDomain(it)}.toList()
}