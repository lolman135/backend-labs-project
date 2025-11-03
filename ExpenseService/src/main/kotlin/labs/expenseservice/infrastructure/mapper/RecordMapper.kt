package labs.expenseservice.infrastructure.mapper

import labs.expenseservice.application.useCase.record.CreateRecordCommand
import labs.expenseservice.domain.record.Record
import labs.expenseservice.infrastructure.dto.request.RecordDtoRequest
import labs.expenseservice.infrastructure.dto.response.RecordDtoResponse
import labs.expenseservice.persistence.record.RecordEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface RecordMapper {

    @Mapping(
        target = "currencyId",
        expression = "java(recordMapperHelper.getCurrencyIdFromUserId(recordDtoRequest.getUserId()))"
    )
    @Mapping(target = "categoryId", expression = "java(recordDtoRequest.getCategoryId())")
    fun toCommand(recordDtoRequest: RecordDtoRequest, recordMapperHelper: RecordMapperHelper): CreateRecordCommand

    @Mapping(target = "user",  expression = "java(recordMapperHelper.getUserInfoFromId(record.getUserId()))")
    @Mapping(target = "category", expression = "java(recordMapperHelper.getCategoryNameFromId(record.getCategoryId()))")
    @Mapping(target = "currency", expression = "java(recordMapperHelper.getCurrencyInfoFromId(record.getCurrencyId()))")
    fun toDto(record: Record, recordMapperHelper: RecordMapperHelper): RecordDtoResponse

    @Mapping(target = "category", expression = "java(recordMapperHelper.getCategoryEntityById(record.getCategoryId()))")
    fun toEntity(record: Record, recordMapperHelper: RecordMapperHelper): RecordEntity

    @Mapping(target = "categoryId", expression = "java(recordEntity.getCategory().getId())")
    fun toDomainFromEntity(recordEntity: RecordEntity): Record
}