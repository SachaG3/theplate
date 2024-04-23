package fr.backend.security

import fr.backend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class JpaUserDetailsService : UserDetailsService {

    @Autowired
    lateinit var userRepository: UserRepository

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(usernameOrEmail: String): UserDetails {

        return userRepository
            .findByUsernameOrEmail(usernameOrEmail)
            .map { AuthUser(it) }
            .orElseThrow { UsernameNotFoundException("User name or email not found: $usernameOrEmail") }
    }
}
