package labs.expenseservice.infrastructure.mapper

import labs.expenseservice.application.useCase.category.UpsertCategoryCommand
import labs.expenseservice.domain.category.Category
import labs.expenseservice.infrastructure.dto.request.CategoryDtoRequest
import labs.expenseservice.infrastructure.dto.response.CategoryDtoResponse
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface CategoryMapper {

    fun toCommand(categoryDtoRequest: CategoryDtoRequest): UpsertCategoryCommand
    fun toDto(category: Category): CategoryDtoResponse
}