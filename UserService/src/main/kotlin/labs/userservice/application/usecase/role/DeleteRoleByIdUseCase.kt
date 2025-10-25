package labs.userservice.application.usecase.role

import labs.userservice.application.usecase.UseCase
import labs.userservice.domain.RoleRepository
import java.util.UUID

class DeleteRoleByIdUseCase(private val roleRepository: RoleRepository) : UseCase<UUID, Unit> {

    override fun execute(input: UUID) {
        roleRepository.deleteById(input)
    }
}