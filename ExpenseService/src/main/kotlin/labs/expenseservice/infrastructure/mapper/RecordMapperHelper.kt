package labs.expenseservice.infrastructure.mapper

import labs.expenseservice.application.useCase.category.GetCategoryByIdUseCase
import labs.expenseservice.application.useCase.record.GetAllRecordsByIdsUseCase
import labs.expenseservice.application.useCase.userExternal.GetUserInfoUseCase
import labs.expenseservice.infrastructure.dto.response.UserSubDto
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class RecordMapperHelper(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getCategoryByIdsUseCase: GetCategoryByIdUseCase
) {
    fun getUserInfoFromId(userId: UUID): UserSubDto {
        val info = getUserInfoUseCase.execute(userId)
        return UserSubDto(info.id, info.name)
    }

    fun getCategoryNameFromId(categoryId: UUID) = getCategoryByIdsUseCase.execute(categoryId).name
}