package labs.authservice.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class AuthDomainTest {

    @Test
    fun `user should correctly add role and change password`() {
        val user = User(UUID.randomUUID(), "Ivan", "ivan@mail.com", "old_pass")
        val role = Role(UUID.randomUUID(), "ROLE_USER")

        val updated = user.changePassword("new_pass").addRole(role)

        assertThat(updated.password).isEqualTo("new_pass")
        assertThat(updated.roles).contains(role)
    }

    @Test
    fun `role should throw exception on blank name`() {
        val role = Role(UUID.randomUUID(), "ADMIN")
        assertThrows<IllegalArgumentException> { role.rename(" ") }
    }
}