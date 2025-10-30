package labs.userservice.application.usecase.role

import labs.userservice.application.exception.EntityAlreadyExistsException
import labs.userservice.application.usecase.UseCase
import labs.userservice.common.toRoleFormat
import labs.userservice.domain.Role
import labs.userservice.domain.RoleRepository
import java.util.UUID

class CreateRoleUseCase(private val roleRepository: RoleRepository) : UseCase<UpsertRoleCommand, Role> {

    override fun execute(input: UpsertRoleCommand): Role {
        if (roleRepository.existsByName(input.name.toRoleFormat()))
            throw EntityAlreadyExistsException("This role already exists")

        val role = Role(id = UUID.randomUUID(), name = input.name.toRoleFormat())
        return roleRepository.save(role)
    }
}