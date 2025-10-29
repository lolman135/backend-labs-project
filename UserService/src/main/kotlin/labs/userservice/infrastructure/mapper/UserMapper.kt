package labs.userservice.infrastructure.mapper

import labs.userservice.application.usecase.user.CreateUserCommand
import labs.userservice.application.usecase.user.UpdateUserCommand
import labs.userservice.domain.User
import labs.userservice.infrastructure.dto.request.UserDtoCreateRequest
import labs.userservice.infrastructure.dto.request.UserDtoUpdateRequest
import labs.userservice.infrastructure.dto.response.UserDtoResponse
import labs.userservice.persistence.user.UserEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring", imports = [labs.userservice.persistence.role.RoleEntity::class])
interface UserMapper {

    fun toCreateCommand(createDto: UserDtoCreateRequest): CreateUserCommand

    fun toUpdateCommand(updateDto: UserDtoUpdateRequest): UpdateUserCommand

    @Mapping(
        target = "roles",
        expression = "java(userMapperHelper" +
                ".getRoleEntitiesFromUserIdList(user.getRoleIds())." +
                "stream().map(RoleEntity::getName).toList())"
    )
    @Mapping(
        target = "defaultCurrency",
        expression = "java(userMapperHelper.getCurrencyCodeFromCurrencyId(user.getDefaultCurrencyId()))"
    )
    fun toDto(user: User, userMapperHelper: UserMapperHelper): UserDtoResponse

    @Mapping(target = "roles", expression = "java(userMapperHelper.getRoleEntitiesFromUserIdList(user.getRoleIds()))")
    @Mapping(target = "defaultCurrencyId", expression = "java(user.getDefaultCurrencyId())")
    fun toEntity(user: User, userMapperHelper: UserMapperHelper): UserEntity

    @Mapping(target = "roleIds", expression = "java(userEntity.getRoles().stream().map(RoleEntity::getId).toList())")
    fun toDomainFromEntity(userEntity: UserEntity): User
}