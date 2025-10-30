package labs.userservice.infrastructure.mapper

import labs.userservice.application.usecase.currencyExternal.GetCurrencyInfoUseCase
import labs.userservice.infrastructure.exception.JpaEntityNotFoundException
import labs.userservice.persistence.role.RoleJpaRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserMapperHelper(
    private val roleJpaRepository: RoleJpaRepository,
    private val getCurrencyInfoUseCase: GetCurrencyInfoUseCase) {

    fun getRoleEntitiesFromUserIdList(roleIds: List<UUID>) = roleIds.map { id ->
            roleJpaRepository.findById(id)
                .orElseThrow {
                    JpaEntityNotFoundException("Role with id=$id not found")
                }
        }.toMutableSet()


    fun getCurrencyCodeFromCurrencyId(currencyId: UUID): String{
        return getCurrencyInfoUseCase.execute(currencyId).code
    }
}