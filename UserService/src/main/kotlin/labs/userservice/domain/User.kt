package labs.userservice.domain

import java.util.UUID

data class User(
    val id: UUID,
    val name: String,
    val email: String,
    val password: String,
    //to simplify development role implements as enum, not separate entity
    val roles: List<Role> = listOf()
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

    fun addRole(newRole: Role): User {
        return copy(roles = roles + newRole)
    }
}
