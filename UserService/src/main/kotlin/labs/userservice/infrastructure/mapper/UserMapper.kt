package labs.userservice.infrastructure.mapper

import labs.userservice.application.usecase.CreateUserCommand
import labs.userservice.application.usecase.UpdateUserCommand
import labs.userservice.domain.Role
import labs.userservice.domain.User
import labs.userservice.infrastructure.dto.request.UserDtoCreateRequest
import labs.userservice.infrastructure.dto.request.UserDtoUpdateRequest
import labs.userservice.infrastructure.dto.response.UserDtoResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface UserMapper {

    fun toCreateCommand(createDto: UserDtoCreateRequest): CreateUserCommand

    @Mapping(target = "roles", expression = "java(toRoles(updateDto.getRoles()))")
    fun toUpdateCommand(updateDto: UserDtoUpdateRequest): UpdateUserCommand

    @Mapping(target = "roles", expression = "java(user.getRoles().stream().map(Role::name).toList())")
    fun toDto(user: User): UserDtoResponse

    fun toRoles(strings: List<String>?): List<Role>? =
        strings?.map { Role.from(it) }
}