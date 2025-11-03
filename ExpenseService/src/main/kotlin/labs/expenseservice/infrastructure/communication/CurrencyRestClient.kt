package labs.expenseservice.infrastructure.communication

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.util.UUID

@Component
class CurrencyRestClient(
    private val restTemplate: RestTemplate,
    @Value("\${currency-service.url}") private val currencyServiceUrl: String
) {
    fun getCurrencyById(currencyId: UUID) =
        restTemplate.getForObject("$currencyServiceUrl/api/v1/currencies/$currencyId", CurrencyDto::class.java)
            ?: throw IllegalStateException("Currency with id=$currencyId not found")
}