package labs.expenseservice.application.useCase.category

import labs.expenseservice.application.exception.EntityNotFoundException
import labs.expenseservice.application.useCase.UseCase
import labs.expenseservice.domain.category.Category
import labs.expenseservice.domain.category.CategoryRepository
import java.util.UUID

class GetCategoryByIdUseCase(private val categoryRepository: CategoryRepository) : UseCase<UUID, Category> {

    override fun execute(id: UUID) = categoryRepository.findById(id)
        ?: throw EntityNotFoundException("Category with id=$id not found")

}