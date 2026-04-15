package labs.expenseservice.domain.category

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class CategoryTest {

    @Test
    fun `should rename category successfully`() {
        val category = Category(UUID.randomUUID(), "Food")
        val updated = category.rename("Grocery")

        assertThat(updated.name).isEqualTo("Grocery")
    }

    @Test
    fun `should throw exception when renaming to blank name`() {
        val category = Category(UUID.randomUUID(), "Food")

        val exception = assertThrows<IllegalArgumentException> {
            category.rename("   ")
        }
        assertThat(exception.message).isEqualTo("Name should not be empty")
    }
}