package labs.userservice.infrastructure.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern

data class UserDtoUpdateRequest(
    @field:Pattern(regexp = "^[a-zA-Zа-яА-ЯіїІЇ_\\d\\s-]{3,40}\$", message = "Invalid username")
    val name: String?,
    @field:Email(message = "Invalid email")
    val email: String?,
    @field:Pattern(regexp = "^[a-zA-Z-_:#\\d%+]{7,30}", message = "Invalid password")
    val password: String?,
    val roles: List<String>?
)