package labs.userservice.infrastructure.dto.request

import jakarta.validation.constraints.*
import org.hibernate.validator.constraints.Currency
import java.util.UUID


data class UserDtoCreateRequest(
    @field:Pattern(regexp = "^[a-zA-Zа-яА-ЯіїІЇ_\\d\\s-]{3,40}$", message = "Invalid username")
    val name: String,
    @field:Email(message = "Invalid email")
    val email: String,
    @field:Pattern(regexp = "^[a-zA-Z-_:#\\d%+]{7,30}", message = "Invalid password")
    val password: String,
    val defaultCurrencyId: UUID?
)