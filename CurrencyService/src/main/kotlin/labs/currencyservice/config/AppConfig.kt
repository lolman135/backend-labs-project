package labs.currencyservice.config

import labs.currencyservice.application.usecase.CreateCurrencyUseCase
import labs.currencyservice.application.usecase.DeleteCurrencyByIdUseCase
import labs.currencyservice.application.usecase.GetAllCurrenciesUseCase
import labs.currencyservice.application.usecase.GetCurrencyByIdUseCase
import labs.currencyservice.application.usecase.UpdateCurrencyByIdUseCase
import labs.currencyservice.domain.CurrencyRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

    @Bean
    fun createCurrencyUseCase(currencyRepository: CurrencyRepository) = CreateCurrencyUseCase(currencyRepository)

    @Bean
    fun getAllCurrenciesUseCase(currencyRepository: CurrencyRepository) = GetAllCurrenciesUseCase(currencyRepository)

    @Bean
    fun getCurrencyByIdUseCase(currencyRepository: CurrencyRepository) = GetCurrencyByIdUseCase(currencyRepository)

    @Bean
    fun updateCurrencyByIdUseCase(currencyRepository: CurrencyRepository) =
        UpdateCurrencyByIdUseCase(currencyRepository)

    @Bean
    fun deleteCurrencyByIdUseCase(currencyRepository: CurrencyRepository) =
        DeleteCurrencyByIdUseCase(currencyRepository)
}