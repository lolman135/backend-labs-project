package labs.expenseservice.application.useCase.category

import labs.expenseservice.application.useCase.UseCase
import labs.expenseservice.domain.category.Category
import labs.expenseservice.domain.category.CategoryRepository

class GetAllCategoriesUseCase(private val categoryRepository: CategoryRepository) : UseCase<Unit, List<Category>> {

    override fun execute(command: Unit) = categoryRepository.findAll()
}