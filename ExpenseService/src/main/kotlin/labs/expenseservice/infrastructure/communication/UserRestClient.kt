package labs.expenseservice.infrastructure.communication

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.util.UUID

@Component
class UserRestClient(
    private val restTemplate: RestTemplate,
    @Value("\${user-service.url}") private val userServiceUrl: String
) {
    fun getUserById(userId: UUID) =
         restTemplate.getForObject("$userServiceUrl/api/v1/users/$userId", UserDto::class.java)
            ?: throw IllegalStateException("User with id=$userId not found")
}