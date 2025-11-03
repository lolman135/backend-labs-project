package labs.expenseservice.persistence.record

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import labs.expenseservice.persistence.category.CategoryEntity
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "records")
class RecordEntity(

    @Id
    @Column(name = "id")
    var id: UUID,

    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: CategoryEntity?,

    @Column(name = "user_id")
    var userId: UUID,

    @Column(name = "currency_id")
    var currencyId: UUID,

    @Column(name = "total_cost")
    var totalCost: Int,

    @Column(name = "creation_time")
    var creationTime: LocalDateTime
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RecordEntity) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}