package labs.expenseservice.config

import labs.expenseservice.application.useCase.category.CreateCategoryUseCase
import labs.expenseservice.application.useCase.category.DeleteCategoryByIdUseCase
import labs.expenseservice.application.useCase.category.GetAllCategoriesUseCase
import labs.expenseservice.application.useCase.category.GetCategoryByIdUseCase
import labs.expenseservice.application.useCase.category.UpdateCategoryByIdUseCase
import labs.expenseservice.application.useCase.record.CreateRecordUseCase
import labs.expenseservice.application.useCase.record.DeleteRecordByIdUseCase
import labs.expenseservice.application.useCase.record.GetAllRecordsUseCase
import labs.expenseservice.application.useCase.record.GetRecordByIdUseCase
import labs.expenseservice.application.useCase.record.UserProvider
import labs.expenseservice.domain.category.CategoryRepository
import labs.expenseservice.domain.record.RecordRepository
import labs.expenseservice.infrastructure.communication.UserProviderImpl
import labs.expenseservice.infrastructure.communication.UserRestClient
import labs.expenseservice.persistence.repository.CategoryMockRepository
import labs.expenseservice.persistence.repository.RecordMockRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class AppConfig {

    @Bean
    fun restTemplate() = RestTemplate()

    //Category
    @Bean
    fun categoryRepository(): CategoryRepository = CategoryMockRepository()

    @Bean
    fun createCategoryUseCase(categoryRepository: CategoryRepository) = CreateCategoryUseCase(categoryRepository)

    @Bean
    fun getCategoryByIdUseCase(categoryRepository: CategoryRepository) = GetCategoryByIdUseCase(categoryRepository)

    @Bean
    fun getAllCategoriesUseCase(categoryRepository: CategoryRepository) = GetAllCategoriesUseCase(categoryRepository)

    @Bean
    fun updateCategoryByIdUseCase(categoryRepository: CategoryRepository) =
        UpdateCategoryByIdUseCase(categoryRepository)

    @Bean
    fun deleteCategoryByIdUseCase(categoryRepository: CategoryRepository) =
        DeleteCategoryByIdUseCase(categoryRepository)

    //Record
    @Bean
    fun recordRepository(): RecordRepository = RecordMockRepository()

    @Bean
    fun createRecordUseCase(
        recordRepository: RecordRepository,
        categoryRepository: CategoryRepository,
        userProvider: UserProvider
    ) = CreateRecordUseCase(recordRepository, categoryRepository, userProvider)

    @Bean
    fun getAllRecordsUseCase(recordRepository: RecordRepository) = GetAllRecordsUseCase(recordRepository)

    @Bean
    fun getRecordByIdUseCase(recordRepository: RecordRepository) = GetRecordByIdUseCase(recordRepository)

    @Bean
    fun deleteRecordById(recordRepository: RecordRepository) = DeleteRecordByIdUseCase(recordRepository)

}