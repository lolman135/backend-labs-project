package labs.userservice.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class UserTest {

    private val userId = UUID.randomUUID()
    private val user = User(userId, "Ivan", "ivan@mail.com", "pass123", listOf(), null)

    @Test
    fun `should correctly update user fields`() {
        val roleId = UUID.randomUUID()
        val currencyId = UUID.randomUUID()

        val updated = user.rename("Dmitry")
            .changeEmail("dima@mail.com")
            .addRole(roleId)
            .changeCurrency(currencyId)

        assertThat(updated.name).isEqualTo("Dmitry")
        assertThat(updated.email).isEqualTo("dima@mail.com")
        assertThat(updated.roleIds).contains(roleId)
        assertThat(updated.defaultCurrencyId).isEqualTo(currencyId)
        assertThat(updated.id).isEqualTo(userId) // ID неизменен
    }

    @Test
    fun `should throw exception for blank values`() {
        assertThrows<IllegalArgumentException> { user.rename(" ") }
        assertThrows<IllegalArgumentException> { user.changeEmail("") }
        assertThrows<IllegalArgumentException> { user.changePassword("  ") }
    }
}