package labs.expenseservice.domain.record

import java.time.LocalDateTime
import java.util.UUID

data class Record(
    val id: UUID,
    val categoryId: UUID,
    val userId: UUID,
    val creationTime: LocalDateTime,
    val totalPrice: Int
) {
    fun changePrice(newPrice: Int): Record {
        require(newPrice > 0){"Price can't be less than zero"}
        return copy(totalPrice = newPrice)
    }
}
