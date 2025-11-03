package labs.currencyservice.infrastructure.dto.inbound

import jakarta.validation.constraints.Pattern

data class CurrencyDtoInbound(

    @field:Pattern(regexp = "^[A-Z]{3}$", message = "Invalid currency code format")
    val code: String,
    @field:Pattern(regexp = "^[a-zA-Z\\s]{1,20}$", message = "Invalid currency name format")
    val name: String,
    @field:Pattern(regexp = "^[$€£¥₹₽₴₩₸₦₱฿₫₪₡₲₵₭₼₺₨₾₳₯₰]$", message = "Invalid currency symbol")
    val symbol: String
)
