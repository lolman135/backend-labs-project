package labs.userservice.web.controller

import jakarta.validation.Valid
import labs.userservice.application.usecase.user.DeleteUserByIdUseCase
import labs.userservice.application.usecase.user.FindAllUsersUseCase
import labs.userservice.application.usecase.user.FindUserByIdUseCase
import labs.userservice.application.usecase.user.UpdateUserByIdUseCase
import labs.userservice.application.usecase.user.UpdateUserCommand
import labs.userservice.infrastructure.dto.request.UserDtoUpdateRequest
import labs.userservice.infrastructure.dto.response.UserDtoResponse
import labs.userservice.infrastructure.mapper.UserMapper
import labs.userservice.infrastructure.mapper.UserMapperHelper
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.UUID

@RestController
@RequestMapping("/api/v1/users")
@Validated
class UserController(
    private val userMapper: UserMapper,
    private val userMapperHelper: UserMapperHelper,
    private val findAllUsersUseCase: FindAllUsersUseCase,
    private val findUserByIdUseCase: FindUserByIdUseCase,
    private val updateUserByIdUseCase: UpdateUserByIdUseCase,
    private val deleteUserByIdUseCase: DeleteUserByIdUseCase
) {

    @GetMapping
    fun findAllUsers(): ResponseEntity<List<UserDtoResponse>> {
        val users = findAllUsersUseCase.execute(Unit)
        val response = users.map { userMapper.toDto(it, userMapperHelper) }
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun findUserById(@PathVariable id: UUID): ResponseEntity<UserDtoResponse> {
        val response = findUserByIdUseCase.execute(id)
        return ResponseEntity.ok(userMapper.toDto(response, userMapperHelper))
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: UUID,
        @RequestBody @Valid updateRequest: UserDtoUpdateRequest
    ): ResponseEntity<UserDtoResponse> {
        val command: UpdateUserCommand = userMapper.toUpdateCommand(updateRequest).copy(id = id)
        val updatedUser = updateUserByIdUseCase.execute(command)
        return ResponseEntity.ok(userMapper.toDto(updatedUser, userMapperHelper))
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: UUID): ResponseEntity<Void> {
        deleteUserByIdUseCase.execute(id)
        return ResponseEntity.noContent().build()
    }
}
