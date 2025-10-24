package labs.userservice.infrastructure.mapper

import labs.userservice.application.usecase.user.CreateUserCommand
import labs.userservice.application.usecase.user.UpdateUserCommand
import labs.userservice.domain.User
import labs.userservice.infrastructure.dto.request.UserDtoCreateRequest
import labs.userservice.infrastructure.dto.request.UserDtoUpdateRequest
import labs.userservice.infrastructure.dto.response.UserDtoResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface UserMapper {

    fun toCreateCommand(createDto: UserDtoCreateRequest): CreateUserCommand

    fun toUpdateCommand(updateDto: UserDtoUpdateRequest): UpdateUserCommand

    @Mapping(target = "roles", expression = "java(user.getRoles().stream().map(Role::name).toList())")
    fun toDto(user: User): UserDtoResponse


}