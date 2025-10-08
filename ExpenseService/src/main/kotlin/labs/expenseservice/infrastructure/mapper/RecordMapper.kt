package labs.expenseservice.infrastructure.mapper

import labs.expenseservice.application.useCase.record.CreateRecordCommand
import labs.expenseservice.infrastructure.dto.request.RecordDtoRequest
import labs.expenseservice.infrastructure.dto.response.RecordDtoResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface RecordMapper {

    fun toCommand(recordDtoRequest: RecordDtoRequest): CreateRecordCommand

    @Mapping(target = "user",  expression = "java(recordMapperHelper.getUserInfoFromId(record.getUserId()))")
    fun toDto(record: Record): RecordDtoResponse
}