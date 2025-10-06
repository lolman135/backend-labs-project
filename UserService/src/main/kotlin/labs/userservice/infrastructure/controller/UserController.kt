package labs.userservice.infrastructure.controller

import jakarta.validation.Valid
import labs.userservice.application.usecase.CreateUserCommand
import labs.userservice.application.usecase.CreateUserUseCase
import labs.userservice.application.usecase.DeleteUserByIdUseCase
import labs.userservice.application.usecase.FindAllUsersUseCase
import labs.userservice.application.usecase.FindUserByIdUseCase
import labs.userservice.application.usecase.UpdateUserByIdUseCase
import labs.userservice.application.usecase.UpdateUserCommand
import labs.userservice.infrastructure.dto.request.UserDtoCreateRequest
import labs.userservice.infrastructure.dto.request.UserDtoUpdateRequest
import labs.userservice.infrastructure.dto.response.UserDtoResponse
import labs.userservice.infrastructure.mapper.UserMapper
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
    private val createUserUseCase: CreateUserUseCase,
    private val findAllUsersUseCase: FindAllUsersUseCase,
    private val findUserByIdUseCase: FindUserByIdUseCase,
    private val updateUserByIdUseCase: UpdateUserByIdUseCase,
    private val deleteUserByIdUseCase: DeleteUserByIdUseCase
) {

    @PostMapping
    fun createUser(@RequestBody @Valid createRequest: UserDtoCreateRequest): ResponseEntity<UserDtoResponse> {
        val command: CreateUserCommand = userMapper.toCreateCommand(createRequest)
        val response = userMapper.toDto(createUserUseCase.execute(command))
        val location = URI.create("/api/v1/users/${response.id}")
        return ResponseEntity.created(location).body(response)
    }

    @GetMapping
    fun findAllUsers(): ResponseEntity<List<UserDtoResponse>> {
        val users = findAllUsersUseCase.execute(Unit)
        val response = users.map { userMapper.toDto(it) }
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun findUserById(@PathVariable id: UUID): ResponseEntity<UserDtoResponse> {
        val response = findUserByIdUseCase.execute(id)
        return ResponseEntity.ok(userMapper.toDto(response))
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: UUID,
        @RequestBody @Valid updateRequest: UserDtoUpdateRequest
    ): ResponseEntity<UserDtoResponse> {
        val command: UpdateUserCommand = userMapper.toUpdateCommand(updateRequest).copy(id = id)
        val updatedUser = updateUserByIdUseCase.execute(command)
        return ResponseEntity.ok(userMapper.toDto(updatedUser))
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: UUID): ResponseEntity<Void> {
        deleteUserByIdUseCase.execute(id)
        return ResponseEntity.noContent().build()
    }
}
