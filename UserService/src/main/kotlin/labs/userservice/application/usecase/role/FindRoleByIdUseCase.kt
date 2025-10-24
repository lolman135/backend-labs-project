package labs.userservice.application.usecase.role

import labs.userservice.application.exception.EntityNotFoundException
import labs.userservice.application.usecase.UseCase
import labs.userservice.domain.Role
import labs.userservice.domain.RoleRepository
import java.util.UUID

class FindRoleByIdUseCase(private val roleRepository: RoleRepository) : UseCase<UUID, Role> {

    override fun execute(input: UUID) = roleRepository.findById(input)
        ?: throw EntityNotFoundException("Role with id=$input not found")
}