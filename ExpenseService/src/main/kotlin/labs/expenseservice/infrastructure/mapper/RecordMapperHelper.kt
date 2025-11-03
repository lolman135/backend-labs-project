package labs.expenseservice.infrastructure.mapper

import labs.expenseservice.application.useCase.category.GetAllCategoriesUseCase
import labs.expenseservice.application.useCase.category.GetCategoryByIdUseCase
import labs.expenseservice.application.useCase.external.GetCurrencyIdFromUserExternalUseCase
import labs.expenseservice.application.useCase.external.GetCurrencyInfoUseCase
import labs.expenseservice.application.useCase.external.GetUserInfoUseCase
import labs.expenseservice.domain.record.Record
import labs.expenseservice.infrastructure.dto.response.CurrencySubDto
import labs.expenseservice.infrastructure.dto.response.UserSubDto
import labs.expenseservice.infrastructure.exception.JpaEntityNotFoundException
import labs.expenseservice.persistence.category.CategoryEntity
import labs.expenseservice.persistence.category.CategoryJpaRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class RecordMapperHelper(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getCategoryByIdsUseCase: GetCategoryByIdUseCase,
    private val getCurrencyIdFromUserExternalUseCase: GetCurrencyIdFromUserExternalUseCase,
    private val getCurrencyInfoUseCase: GetCurrencyInfoUseCase,
    private val categoryJpaRepository: CategoryJpaRepository,
) {
    fun getUserInfoFromId(userId: UUID): UserSubDto {
        val info = getUserInfoUseCase.execute(userId)
        return UserSubDto(info.id, info.name)
    }

    fun getCurrencyInfoFromId(currencyId: UUID): CurrencySubDto {
        val info = getCurrencyInfoUseCase.execute(currencyId)
        return CurrencySubDto(info.id, info.code)
    }

    fun getCurrencyIdFromUserId(userId: UUID) = getCurrencyIdFromUserExternalUseCase.execute(userId)

    fun getCategoryNameFromId(categoryId: UUID): String {
        return getCategoryByIdsUseCase.execute(categoryId).name
    }

    fun getCategoryEntityById(categoryId: UUID): CategoryEntity = categoryJpaRepository.findById(categoryId)
        .orElseThrow {
            JpaEntityNotFoundException("Role with id=$categoryId not found")
        }
}