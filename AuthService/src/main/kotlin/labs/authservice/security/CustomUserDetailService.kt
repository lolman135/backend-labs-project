package labs.authservice.security

import labs.authservice.application.exception.EntityNotFoundException
import labs.authservice.domain.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CustomUserDetailService(private val userRepository: UserRepository) : UserDetailsService{

    override fun loadUserByUsername(email: String): UserDetails {
        val user = (userRepository.findUserByEmail(email)
            ?: throw EntityNotFoundException("User with this email does not exists"))

        return CustomUserDetails(user)
    }

    fun loadUserById(id: UUID): UserDetails {
        val user = (userRepository.findById(id)
            ?: throw EntityNotFoundException("This user not found"))

        return CustomUserDetails(user)
    }
}