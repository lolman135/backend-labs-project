package labs.authservice.infrastructure.mapper

import labs.authservice.domain.Role
import labs.authservice.persistence.RoleEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface RoleMapper {

    fun toDomain(roleEntity: RoleEntity): Role
    fun toEntity(role: Role): RoleEntity
}