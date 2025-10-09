package labs.expenseservice.web.controller

import jakarta.validation.Valid
import labs.expenseservice.application.useCase.category.CreateCategoryUseCase
import labs.expenseservice.application.useCase.category.DeleteCategoryByIdUseCase
import labs.expenseservice.application.useCase.category.GetAllCategoriesUseCase
import labs.expenseservice.application.useCase.category.GetCategoryByIdUseCase
import labs.expenseservice.application.useCase.category.UpdateCategoryByIdUseCase
import labs.expenseservice.infrastructure.dto.request.CategoryDtoRequest
import labs.expenseservice.infrastructure.dto.response.CategoryDtoResponse
import labs.expenseservice.infrastructure.mapper.CategoryMapper
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.UUID

@RestController
@RequestMapping("/api/v1/categories")
@Validated
class CategoryController(
    private val categoryMapper: CategoryMapper,
    private val createCategoryUseCase: CreateCategoryUseCase,
    private val getCategoryByIdUseCase: GetCategoryByIdUseCase,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val updateCategoryByIdUseCase: UpdateCategoryByIdUseCase,
    private val deleteCategoryByIdUseCase: DeleteCategoryByIdUseCase
) {

    @PostMapping
    fun save(@RequestBody @Valid request: CategoryDtoRequest): ResponseEntity<CategoryDtoResponse>{
        val command = categoryMapper.toCommand(request)
        val response = categoryMapper.toDto(createCategoryUseCase.execute(command))
        val location = URI.create("/api/v1/categories/${response.id}")
        return ResponseEntity.created(location).body(response)
    }

    @GetMapping
    fun getAll() = ResponseEntity
        .ok(getAllCategoriesUseCase.execute(Unit).map { categoryMapper.toDto(it) })

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID) =
        ResponseEntity.ok(categoryMapper.toDto(getCategoryByIdUseCase.execute(id)))

    @PutMapping("/{id}")
    fun updateById(
        @PathVariable id: UUID,
        @RequestBody @Valid request: CategoryDtoRequest
    ): ResponseEntity<CategoryDtoResponse> {
        val pair = id to categoryMapper.toCommand(request)
        val response = categoryMapper.toDto(updateCategoryByIdUseCase.execute(pair))
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: UUID): ResponseEntity<Void>{
        deleteCategoryByIdUseCase.execute(id)
        return ResponseEntity.noContent().build()
    }
}