package labs.currencyservice.application.usecase

data class UpsertCurrencyCommand(
    val code: String,
    val name: String,
    val symbol: String
)
