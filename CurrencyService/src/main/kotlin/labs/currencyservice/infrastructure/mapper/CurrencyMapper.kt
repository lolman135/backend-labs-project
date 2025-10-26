package labs.currencyservice.infrastructure.mapper


import labs.currencyservice.application.usecase.UpsertCurrencyCommand
import labs.currencyservice.domain.Currency
import labs.currencyservice.infrastructure.dto.inbound.CurrencyDtoInbound
import labs.currencyservice.infrastructure.dto.outbound.CurrencyDtoOutbound
import labs.currencyservice.persistence.CurrencyEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface CurrencyMapper {

    fun toEntity(domain: Currency): CurrencyEntity
    fun toDomainFromEntity(entity: CurrencyEntity): Currency
    fun toCommand(dto: CurrencyDtoInbound): UpsertCurrencyCommand
    fun toDto(currency: Currency): CurrencyDtoOutbound
}