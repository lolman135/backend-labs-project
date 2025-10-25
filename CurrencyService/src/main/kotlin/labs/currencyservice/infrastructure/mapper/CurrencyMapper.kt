package labs.currencyservice.infrastructure.mapper


import labs.currencyservice.domain.Currency
import labs.currencyservice.persistence.CurrencyEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface CurrencyMapper {

    fun toEntity(domain: Currency): CurrencyEntity
    fun toDomainFromEntity(entity: CurrencyEntity): Currency
}