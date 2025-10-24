package labs.userservice.application.usecase.user

import java.util.UUID

data class UpdateUserCommand(
    val id: UUID?,
    val name: String?,
    val email: String?,
    val password: String?,
    val rolesId: List<UUID>?,
    val defaultCurrencyId: UUID?
)