package labs.expenseservice.application.useCase.category

import labs.expenseservice.application.exception.EntityAlreadyExistsException
import labs.expenseservice.domain.category.Category
import labs.expenseservice.domain.category.CategoryRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*

class CreateCategoryUseCaseTest {

    private val repository: CategoryRepository = mock()
    private val useCase = CreateCategoryUseCase(repository)

    @Test
    fun `should create category when it does not exist`() {
        val command = UpsertCategoryCommand("Transport")
        whenever(repository.existsByName("Transport")).thenReturn(false)
        whenever(repository.save(any())).thenAnswer { it.arguments[0] }

        val result = useCase.execute(command)

        assertThat(result.name).isEqualTo("Transport")
        verify(repository).save(any())
    }

    @Test
    fun `should throw exception if category name already exists`() {
        val command = UpsertCategoryCommand("Food")
        whenever(repository.existsByName("Food")).thenReturn(true)

        assertThrows<EntityAlreadyExistsException> {
            useCase.execute(command)
        }
        verify(repository, never()).save(any())
    }
}