package labs.currencyservice.persistence

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "currencies")
class CurrencyEntity(
    @get:Id
    @get:Column(name = "id")
    var id: UUID? = null,

    @get:Column(name="code")
    var code: String,

    @get:Column(name="name")
    var name: String,

    @get:Column(name="symbol")
    var symbol: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CurrencyEntity) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}