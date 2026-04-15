package labs.userservice.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class RoleTest {

    @Test
    fun `should rename role when name is valid`() {
        val role = Role(UUID.randomUUID(), "ROLE_USER")
        val updatedRole = role.rename("ROLE_ADMIN")

        assertThat(updatedRole.name).isEqualTo("ROLE_ADMIN")
        assertThat(updatedRole.id).isEqualTo(role.id) // ID не должен меняться
    }

    @Test
    fun `should throw exception when renaming to blank name`() {
        val role = Role(UUID.randomUUID(), "ROLE_USER")

        assertThrows<IllegalArgumentException> {
            role.rename("  ")
        }
    }
}