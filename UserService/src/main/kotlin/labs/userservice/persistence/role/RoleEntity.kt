package labs.userservice.persistence.role

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "roles")
class RoleEntity(
    @Id
    @Column(name = "id")
    var id: UUID? = null,

    @Column(name = "name")
    var name: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RoleEntity) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "RoleEntity(id=$id, name='$name')"
    }


}