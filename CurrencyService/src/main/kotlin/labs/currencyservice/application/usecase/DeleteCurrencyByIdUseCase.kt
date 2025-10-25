package labs.currencyservice.application.usecase

import labs.currencyservice.domain.CurrencyRepository
import java.util.UUID

class DeleteCurrencyByIdUseCase(private val currencyRepository: CurrencyRepository) : UseCase<UUID, Unit> {

    override fun execute(input: UUID) {
        currencyRepository.deleteById(input)
    }
}