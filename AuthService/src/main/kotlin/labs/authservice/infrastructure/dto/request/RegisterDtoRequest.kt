package labs.authservice.infrastructure.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

class RegisterDtoRequest(

    @field:Pattern(
        regexp = "^[a-zA-Zа-яА-ЯіІїЇʼєЄ\\d\\s]{2,}$",
        message = "Invalid name format")
    val name: String,

    @field:NotBlank(message = "Please enter the email")
    @field:Email(message = "Invalid email format")
    val email: String,

    @field:Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,24}$",
        message = "Password must contains at least 8 character (max 24), " +
                "include lower and upper case latin letters and numbers")
    val password: String
)