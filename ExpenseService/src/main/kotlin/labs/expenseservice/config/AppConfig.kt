package labs.expenseservice.config

import labs.expenseservice.application.useCase.CurrencyProvider
import labs.expenseservice.application.useCase.category.CreateCategoryUseCase
import labs.expenseservice.application.useCase.category.DeleteCategoryByIdUseCase
import labs.expenseservice.application.useCase.category.GetAllCategoriesUseCase
import labs.expenseservice.application.useCase.category.GetCategoryByIdUseCase
import labs.expenseservice.application.useCase.category.UpdateCategoryByIdUseCase
import labs.expenseservice.application.useCase.record.CreateRecordUseCase
import labs.expenseservice.application.useCase.record.DeleteRecordByIdUseCase
import labs.expenseservice.application.useCase.record.GetRecordByIdUseCase
import labs.expenseservice.application.useCase.UserProvider
import labs.expenseservice.application.useCase.external.GetCurrencyIdFromUserExternalUseCase
import labs.expenseservice.application.useCase.external.GetCurrencyInfoUseCase
import labs.expenseservice.application.useCase.record.GetAllRecordsByIdsUseCase
import labs.expenseservice.application.useCase.external.GetUserInfoUseCase
import labs.expenseservice.domain.category.CategoryRepository
import labs.expenseservice.domain.record.RecordRepository
import labs.expenseservice.infrastructure.mapper.CategoryMapper
import labs.expenseservice.infrastructure.mapper.RecordMapper
import labs.expenseservice.infrastructure.mapper.RecordMapperHelper
import labs.expenseservice.persistence.category.CategoryJpaRepository
import labs.expenseservice.persistence.category.CategoryRepositoryImpl
import labs.expenseservice.persistence.record.RecordJpaRepository
import labs.expenseservice.persistence.record.RecordRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import java.util.UUID

@Configuration
class AppConfig {

    @Bean
    fun restTemplate() = RestTemplate()

    //Category
    @Bean
    fun categoryRepository(
        categoryJpaRepository: CategoryJpaRepository,
        categoryMapper: CategoryMapper
    ) : CategoryRepository =
        CategoryRepositoryImpl(categoryJpaRepository, categoryMapper)

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
    fun recordRepository(
        recordJpaRepository: RecordJpaRepository,
        recordMapper: RecordMapper,
        recordMapperHelper: RecordMapperHelper
    ): RecordRepository = RecordRepositoryImpl(recordJpaRepository, recordMapper, recordMapperHelper)

    @Bean
    fun createRecordUseCase(
        recordRepository: RecordRepository,
        categoryRepository: CategoryRepository,
        currencyProvider: CurrencyProvider,
        userProvider: UserProvider
    ) = CreateRecordUseCase(recordRepository, categoryRepository, userProvider, currencyProvider)

    @Bean
    fun getRecordByIdUseCase(recordRepository: RecordRepository) = GetRecordByIdUseCase(recordRepository)

    @Bean
    fun deleteRecordById(recordRepository: RecordRepository) = DeleteRecordByIdUseCase(recordRepository)

    @Bean
    fun getAllRecordsByIdsUseCase(recordRepository: RecordRepository) = GetAllRecordsByIdsUseCase(recordRepository)

    @Bean
    fun getUserInfoUseCase(userProvider: UserProvider) = GetUserInfoUseCase(userProvider)

    @Bean
    fun getCurrencyInfoUseCase(currencyProvider: CurrencyProvider) = GetCurrencyInfoUseCase(currencyProvider)

    @Bean
    fun getCurrencyIdFromUserExternalUseCase(userProvider: UserProvider) =
        GetCurrencyIdFromUserExternalUseCase(userProvider)

}