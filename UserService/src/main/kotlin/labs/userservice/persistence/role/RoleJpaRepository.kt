package labs.userservice.persistence.role

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface RoleJpaRepository : JpaRepository<RoleEntity, UUID> {

    fun existsRoleEntityByName(name: String): Boolean
    fun findRoleEntityByName(name: String): Optional<RoleEntity>
}