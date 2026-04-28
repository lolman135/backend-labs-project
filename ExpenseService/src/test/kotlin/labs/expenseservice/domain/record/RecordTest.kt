package labs.expenseservice.domain.record

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.util.UUID

class RecordTest {

    @Test
    fun `should change price when value is positive`() {
        val record = Record(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now(), 100, UUID.randomUUID())

        val updated = record.changePrice(200)

        assertThat(updated.totalCost).isEqualTo(200)
    }

    @Test
    fun `should throw exception when price is zero or negative`() {
        val record = Record(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now(), 100, UUID.randomUUID())

        assertThrows<IllegalArgumentException> { record.changePrice(0) }
        assertThrows<IllegalArgumentException> { record.changePrice(-1) }
    }
}