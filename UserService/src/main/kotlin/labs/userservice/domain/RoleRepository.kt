package labs.userservice.domain

import java.util.UUID

interface RoleRepository {

    fun save(user: Role): Role
    fun findAll(): List<Role>
    fun findById(id: UUID): Role?
    fun deleteById(id: UUID)
    fun existsById(id: UUID): Boolean
    fun existsByName(name: String): Boolean
    fun getDefaultRole(): Role?
}