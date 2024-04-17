package fr.backend.security

import fr.backend.models.User
import fr.backend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.stream.Collectors


@Service
class AuthService {

    @Autowired
    private val jwtEncoder: JwtEncoder? = null

    @Autowired
    lateinit var JwtDecoder: JwtDecoder


    @Autowired
    private val userRepository: UserRepository? = null

    fun generateToken(authentication: Authentication): String {
        val now = Instant.now()

        val scope: String = authentication.authorities
            .stream()
            .map { obj: GrantedAuthority -> obj.authority }
            .collect(Collectors.joining(" "))

        val claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plus(10, ChronoUnit.HOURS))
            .subject(authentication.name)
            .claim("scope", scope)
            .claim("user_id", (authentication.principal as AuthUser).user.id)
            .claim("role", (authentication.principal as AuthUser).user.role.name)
            .build()

        return jwtEncoder!!.encode(JwtEncoderParameters.from(claims)).tokenValue
    }

    //Exemple de récupération de données dans le token JWT
    fun getActiveUser(token: String): User {
        val claims = JwtDecoder.decode(token).claims
        val userId = claims["user_id"] as UUID
        return userRepository!!.findById(userId).orElseThrow { RuntimeException("User not found") }
    }
}