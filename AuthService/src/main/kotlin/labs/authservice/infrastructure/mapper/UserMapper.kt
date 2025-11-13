package labs.authservice.infrastructure.mapper

import labs.authservice.domain.User
import labs.authservice.persistence.UserEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface UserMapper {

    @Mapping(target = "roles", expression = "java(userMapperHelper.roleEntitiesToRoles(userEntity.getRoles()))")
    fun toDomain(userEntity: UserEntity, userMapperHelper: UserMapperHelper): User

    @Mapping(target = "roles", expression = "java(userMapperHelper.rolesToRoleEntities(user.getRoles()))")
    fun toEntity(user: User, userMapperHelper: UserMapperHelper): UserEntity
}