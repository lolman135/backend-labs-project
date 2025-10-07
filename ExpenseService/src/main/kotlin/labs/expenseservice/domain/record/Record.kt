package labs.expenseservice.domain.record

import java.time.LocalDateTime
import java.util.UUID

data class Record(
    val id: UUID,
    val categoryId: UUID,
    val userId: UUID,
    val creationTime: LocalDateTime,
    val totalCost: Int
) {
    fun changePrice(newCost: Int): Record {
        require(newCost > 0){"Price can't be less than zero"}
        return copy(totalCost = newCost)
    }
}
