package labs.userservice.persistence.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserJpaRepository : JpaRepository<UserEntity, UUID> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM UserEntity u WHERE u.name = :name OR u.email = :email")
    fun existsUserEntityByNameOrEmail(name: String, email: String): Boolean
}