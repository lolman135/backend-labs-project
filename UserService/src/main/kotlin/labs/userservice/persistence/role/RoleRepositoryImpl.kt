package labs.userservice.persistence.role

import labs.userservice.domain.Role
import labs.userservice.domain.RoleRepository
import labs.userservice.infrastructure.mapper.RoleMapper
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class RoleRepositoryImpl(
    private val roleJpaRepository: RoleJpaRepository,
    private val roleMapper: RoleMapper
) : RoleRepository {

    override fun deleteById(id: UUID) {
        roleJpaRepository.deleteById(id)
    }

    override fun save(role: Role): Role {
        val entity = roleMapper.toEntity(role)
        return roleMapper.toDomainFromEntity(roleJpaRepository.save(entity))
    }

    override fun findAll() = roleJpaRepository.findAll().map { roleMapper.toDomainFromEntity(it) }

    override fun findById(id: UUID): Role? {
        val optionalEntity = roleJpaRepository.findById(id)
        return optionalEntity.map { roleMapper.toDomainFromEntity(it) }.orElse(null)
    }

    override fun existsById(id: UUID) = roleJpaRepository.existsById(id)

    override fun existsByName(name: String) = roleJpaRepository.existsRoleEntityByName(name)

    override fun getDefaultRole(): Role? {
        val optionalEntity = roleJpaRepository.findRoleEntityByName("ROLE_USER")
        return optionalEntity.map { roleMapper.toDomainFromEntity(it) }.orElse(null)
    }
}