package labs.userservice.application.usecase.role

import labs.userservice.application.usecase.UseCase
import labs.userservice.domain.Role
import labs.userservice.domain.RoleRepository

class FindAllRolesUseCase(private val roleRepository: RoleRepository) : UseCase<Unit, List<Role>> {

    override fun execute(input: Unit) = roleRepository.findAll()
}