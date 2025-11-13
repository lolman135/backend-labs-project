package labs.authservice.persistence

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.intellij.lang.annotations.Identifier
import java.util.UUID

@Entity
@Table(name = "roles")
class RoleEntity (

    @get:Id
    var id: UUID? = null,
    var name: String
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RoleEntity

        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}