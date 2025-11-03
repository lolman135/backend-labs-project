package labs.expenseservice.web.controller

import labs.expenseservice.application.useCase.record.CreateRecordUseCase
import labs.expenseservice.application.useCase.record.DeleteRecordByIdUseCase
import labs.expenseservice.application.useCase.record.GetAllRecordsByIdsCommand
import labs.expenseservice.application.useCase.record.GetAllRecordsByIdsUseCase
import labs.expenseservice.application.useCase.record.GetRecordByIdUseCase
import labs.expenseservice.infrastructure.dto.request.RecordDtoRequest
import labs.expenseservice.infrastructure.dto.response.RecordDtoResponse
import labs.expenseservice.infrastructure.mapper.RecordMapper
import labs.expenseservice.infrastructure.mapper.RecordMapperHelper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.UUID

@RestController
@RequestMapping("/api/v1/records")
class RecordController(
    private val recordMapper: RecordMapper,
    private val recordMapperHelper: RecordMapperHelper,
    private val createRecordUseCase: CreateRecordUseCase,
    private val getAllRecordsByIdsUseCase: GetAllRecordsByIdsUseCase,
    private val getRecordByIdUseCase: GetRecordByIdUseCase,
    private val deleteRecordByIdUseCase: DeleteRecordByIdUseCase
) {

    @PostMapping
    fun save(@RequestBody recordDtoRequest: RecordDtoRequest): ResponseEntity<RecordDtoResponse> {
        val command = recordMapper.toCommand(recordDtoRequest, recordMapperHelper)
        val response = recordMapper.toDto(createRecordUseCase.execute(command), recordMapperHelper)
        val location = URI.create("/api/v1/records/${response.id}")
        return ResponseEntity.created(location).body(response)
    }

    @GetMapping
    fun getAllByUserAndCategoryIds(
        @RequestParam userId: UUID?,
        @RequestParam categoryId: UUID?
    ): ResponseEntity<List<RecordDtoResponse>> {
        return ResponseEntity.ok(
            getAllRecordsByIdsUseCase.execute(GetAllRecordsByIdsCommand(userId=userId, categoryId = categoryId)).map {
                recordMapper.toDto(it, recordMapperHelper)
            }
        )
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID) =
        ResponseEntity.ok(recordMapper.toDto(getRecordByIdUseCase.execute(id), recordMapperHelper))

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: UUID): ResponseEntity<Void>{
        deleteRecordByIdUseCase.execute(id)
        return ResponseEntity.noContent().build()
    }
}