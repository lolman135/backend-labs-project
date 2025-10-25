package labs.userservice.persistance

import labs.userservice.domain.Role
import labs.userservice.domain.RoleRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class RoleMockRepository : RoleRepository {

    override fun deleteById(id: UUID) {
        TODO("Not yet implemented")
    }

    override fun save(user: Role): Role {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Role> {
        TODO("Not yet implemented")
    }

    override fun findById(id: UUID): Role? {
        TODO("Not yet implemented")
    }

    override fun existsById(id: UUID): Boolean {
        TODO("Not yet implemented")
    }

    override fun existsByName(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun getDefaultRole(): Role? {
        TODO("Not yet implemented")
    }
}