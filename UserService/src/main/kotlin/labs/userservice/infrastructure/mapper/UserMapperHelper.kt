package labs.userservice.infrastructure.mapper

import labs.userservice.application.usecase.CurrencyProvider
import labs.userservice.application.usecase.currencyExternal.CurrencyInfo
import labs.userservice.infrastructure.exception.JpaEntityNotFoundException
import labs.userservice.persistence.role.RoleEntity
import labs.userservice.persistence.role.RoleJpaRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserMapperHelper(
    private val roleJpaRepository: RoleJpaRepository,
    private val currencyProvider: CurrencyProvider) {

    fun getRoleEntitiesFromUserIdList(roleIds: List<UUID>) = roleIds.map { id ->
            roleJpaRepository.findById(id)
                .orElseThrow {
                    JpaEntityNotFoundException("Role with id=$id not found")
                }
        }.toMutableSet()


    fun getCurrencyCodeFromCurrencyId(currencyId: UUID): String{
        return currencyProvider.getCurrencyInfoById(currencyId).code
    }
}