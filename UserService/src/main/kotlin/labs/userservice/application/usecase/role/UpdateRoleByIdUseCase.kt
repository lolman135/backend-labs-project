package labs.userservice.application.usecase.role

import labs.userservice.application.exception.EntityAlreadyExistsException
import labs.userservice.application.exception.EntityNotFoundException
import labs.userservice.application.usecase.UseCase
import labs.userservice.domain.Role
import labs.userservice.domain.RoleRepository
import java.util.UUID

class UpdateRoleByIdUseCase(
    private val roleRepository: RoleRepository
) : UseCase<Pair<UUID, UpsertRoleCommand>, Role> {

    override fun execute(input: Pair<UUID, UpsertRoleCommand>): Role {
        val(id, executingCommand) = input
        val existingRole = roleRepository.findById(id) ?: throw EntityNotFoundException("Role wit id=$id not found")

        if(existingRole.name != executingCommand.name && roleRepository.existsByName(executingCommand.name))
            throw EntityAlreadyExistsException("This role already exists")

        val updatedRole = existingRole.rename(executingCommand.name)
        return roleRepository.save(updatedRole)
    }
}