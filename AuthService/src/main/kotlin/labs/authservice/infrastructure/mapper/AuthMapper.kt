package labs.authservice.infrastructure.mapper

import labs.authservice.application.usecase.LoginUserCommand
import labs.authservice.application.usecase.RegisterUserCommand
import labs.authservice.infrastructure.dto.request.LoginDtoRequest
import labs.authservice.infrastructure.dto.request.RegisterDtoRequest
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface AuthMapper {

    fun toLoginCommand(logiRequest: LoginDtoRequest): LoginUserCommand
    fun toRegisterCommand(registerRequest: RegisterDtoRequest): RegisterUserCommand
}