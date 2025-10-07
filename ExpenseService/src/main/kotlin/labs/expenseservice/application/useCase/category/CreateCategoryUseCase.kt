package labs.expenseservice.application.useCase.category

import labs.expenseservice.application.exception.EntityAlreadyExistsException
import labs.expenseservice.application.useCase.UseCase
import labs.expenseservice.domain.category.Category
import labs.expenseservice.domain.category.CategoryRepository
import java.util.UUID

class CreateCategoryUseCase(private val categoryRepository: CategoryRepository)
    : UseCase<UpsertCategoryCommand, Category> {

    override fun execute(command: UpsertCategoryCommand): Category{
        if (categoryRepository.existsByName(command.name))
            throw EntityAlreadyExistsException("This Category already exists")

        val category = Category(UUID.randomUUID(), command.name)

        return categoryRepository.save(category)
    }
}