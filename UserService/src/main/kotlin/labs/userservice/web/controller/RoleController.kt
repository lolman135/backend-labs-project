package labs.userservice.web.controller

import jakarta.validation.Valid
import labs.userservice.application.usecase.role.CreateRoleUseCase
import labs.userservice.application.usecase.role.DeleteRoleByIdUseCase
import labs.userservice.application.usecase.role.FindAllRolesUseCase
import labs.userservice.application.usecase.role.FindRoleByIdUseCase
import labs.userservice.application.usecase.role.UpdateRoleByIdUseCase
import labs.userservice.infrastructure.dto.request.RoleDtoRequest
import labs.userservice.infrastructure.dto.response.RoleDtoResponse
import labs.userservice.infrastructure.mapper.RoleMapper
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.UUID

@RestController
@RequestMapping("/api/v1/roles")
@Validated
class RoleController(
    private val roleMapper: RoleMapper,
    private val createRoleUseCase: CreateRoleUseCase,
    private val findAllRoleUseCase: FindAllRolesUseCase,
    private val findRoleByIdUseCase: FindRoleByIdUseCase,
    private val updateRoleByIdUseCase: UpdateRoleByIdUseCase,
    private val deleteRoleByIdUseCase: DeleteRoleByIdUseCase
) {

    @PostMapping
    fun create(@Valid @RequestBody request: RoleDtoRequest): ResponseEntity<RoleDtoResponse> {
        val command = roleMapper.toCommand(request)
        val response = roleMapper.toDto(createRoleUseCase.execute(command))
        val location = URI.create("/api/v1/roles")
        return ResponseEntity.created(location).body(response)
    }

    @GetMapping
    fun getAll() = ResponseEntity.ok(findAllRoleUseCase.execute(Unit).map { roleMapper.toDto(it) })

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID) = ResponseEntity.ok(roleMapper.toDto(findRoleByIdUseCase.execute(id)))

    @PutMapping("/{id}")
    fun updateById(
        @PathVariable id: UUID,
        @Valid @RequestBody request: RoleDtoRequest
    ): ResponseEntity<RoleDtoResponse> {
        val command = roleMapper.toCommand(request)
        return ResponseEntity.ok(roleMapper.toDto(updateRoleByIdUseCase.execute(Pair(id, command))))
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: UUID): ResponseEntity<Void> {
        deleteRoleByIdUseCase.execute(id)
        return ResponseEntity.noContent().build()
    }
}

