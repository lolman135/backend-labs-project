package labs.userservice.persistence.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import labs.userservice.persistence.role.RoleEntity
import java.util.UUID

@Entity
@Table(name = "users")
class UserEntity(
    @get:Id
    @get:Column(name = "id")
    var id: UUID? = null,

    @get:Column(name = "name")
    var name: String,

    @get:Column(name = "email")
    var email: String,

    @get:Column(name = "password")
    var password: String,

    @get:ManyToMany
    @get:JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: MutableSet<RoleEntity>? = mutableSetOf(),

    @get:Column(name = "currency_id")
    var defaultCurrencyId: UUID?
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserEntity) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}