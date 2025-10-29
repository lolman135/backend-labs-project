package labs.userservice.infrastructure.dto.request

import jakarta.validation.constraints.Pattern

data class RoleDtoRequest(
    @field:Pattern(regexp = "^[a-zA-Z]{3,25}$", message = "Invalid Role name")
    val name: String
)