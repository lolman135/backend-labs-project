package labs.expenseservice.persistence.category

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "categories")
class CategoryEntity(

    @Id
    @Column(name = "id")
    var id: UUID,

    @Column(name = "name")
    var name: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CategoryEntity) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}