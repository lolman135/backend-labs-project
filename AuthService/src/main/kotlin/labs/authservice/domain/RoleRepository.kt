package labs.authservice.domain

interface RoleRepository {

    fun getDefaultRole(): Role?
}