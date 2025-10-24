package labs.userservice.domain

import java.util.UUID

data class User(
    val id: UUID,
    val name: String,
    val email: String,
    val password: String,
    val roleIds: List<UUID> = listOf(),
    val defaultCurrencyId: UUID
) {
    fun rename(newName: String): User{
        require(newName.isNotBlank()){"Name cannot be empty"}
        return copy(name = newName)
    }

    fun changeEmail(newEmail: String): User{
        require(newEmail.isNotBlank()){"Email cannot be empty"}
        return copy(email = newEmail)
    }

    fun changePassword(newPassword: String): User{
        require(newPassword.isNotBlank()){"Password cannot be empty"}
        return copy(password = newPassword)
    }

    fun addRole(newRoleId: UUID): User {
        return copy(roleIds = roleIds + id)
    }

    fun changeCurrency(newCurrencyId: UUID): User {
        return copy(defaultCurrencyId = newCurrencyId)
    }
}
