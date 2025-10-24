package labs.expenseservice.application.useCase.category


import labs.expenseservice.application.exception.EntityAlreadyExistsException
import labs.expenseservice.application.exception.EntityNotFoundException
import labs.expenseservice.application.useCase.UseCase
import labs.expenseservice.domain.category.Category
import labs.expenseservice.domain.category.CategoryRepository
import java.util.UUID

class UpdateCategoryByIdUseCase(private val categoryRepository: CategoryRepository)
    : UseCase<Pair<UUID, UpsertCategoryCommand>, Category> {

    override fun execute(command: Pair<UUID, UpsertCategoryCommand>): Category{
        val (id, executingCommand) = command

        val category = categoryRepository.findById(id)
            ?: throw EntityNotFoundException("Category with id=$id not found")

        if (categoryRepository.existsByName(executingCommand.name) && category.name != executingCommand.name)
            throw EntityAlreadyExistsException("This Category already exists")

        val updatedCategory = category.rename(executingCommand.name)
        return categoryRepository.save(updatedCategory)
    }
}