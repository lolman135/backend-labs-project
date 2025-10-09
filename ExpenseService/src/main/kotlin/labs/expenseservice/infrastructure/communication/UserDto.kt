package labs.expenseservice.infrastructure.communication

import java.util.UUID

data class UserDto(
    val id: UUID,
    val name: String,
    val email: String,
    val roles: List<String>
)