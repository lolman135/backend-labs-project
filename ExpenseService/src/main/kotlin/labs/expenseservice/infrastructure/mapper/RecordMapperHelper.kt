package labs.expenseservice.infrastructure.mapper

import labs.expenseservice.application.useCase.userExternal.GetUserInfoUseCase
import labs.expenseservice.infrastructure.dto.response.UserSubDto
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class RecordMapperHelper(
    private val getUserInfoUseCase: GetUserInfoUseCase
) {
    fun getUserInfoFromId(userId: UUID): UserSubDto {
        val info = getUserInfoUseCase.execute(userId)
        return UserSubDto(info.id, info.name)
    }
}