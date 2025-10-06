package labs.userservice.application.usecase

import labs.userservice.domain.Role
import java.util.UUID

data class UpdateUserCommand(
    val id: UUID?,
    val name: String?,
    val email: String?,
    val password: String?,
    val roles: List<Role>?
)