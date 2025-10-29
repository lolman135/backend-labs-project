package labs.userservice.infrastructure.mapper

import labs.userservice.application.usecase.role.UpsertRoleCommand
import labs.userservice.domain.Role
import labs.userservice.infrastructure.dto.request.RoleDtoRequest
import labs.userservice.infrastructure.dto.response.RoleDtoResponse
import labs.userservice.persistence.role.RoleEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface RoleMapper {

    fun toEntity(domain: Role): RoleEntity
    fun toDomainFromEntity(entity: RoleEntity): Role
    fun toCommand(dto: RoleDtoRequest): UpsertRoleCommand
    fun toDto(role: Role): RoleDtoResponse
}