package labs.authservice.persistence

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import labs.authservice.domain.Role
import java.util.UUID

@Entity
@Table(name = "users")
class UserEntity(

    @get:Id
    var id: UUID? = null,
    var name: String,
    var email: String,
    var password: String,

    @get:ManyToMany
    @get:JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: MutableSet<RoleEntity>? = mutableSetOf()
)
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserEntity

        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}