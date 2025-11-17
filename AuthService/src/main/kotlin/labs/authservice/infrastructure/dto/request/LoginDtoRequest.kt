package labs.authservice.infrastructure.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class LoginDtoRequest(

    @field:NotBlank(message = "Please enter the email")
    @field:Email(message = "Invalid email format")
    val email: String,

    @field:NotBlank(message = "Please enter the password")
    val password: String
)