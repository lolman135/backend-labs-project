package labs.expenseservice.application.useCase.category

import labs.expenseservice.application.useCase.UseCase
import labs.expenseservice.domain.category.CategoryRepository
import java.util.UUID

class DeleteCategoryByIdUseCase(private val categoryRepository: CategoryRepository) : UseCase<UUID, Unit> {
    override fun execute(id: UUID) = categoryRepository.deleteById(id)
}