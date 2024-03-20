package fr.backend.faker

import com.github.javafaker.Faker
import fr.backend.models.Role
import fr.backend.models.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class DataFaker {

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder // Assurez-vous que l'injection se fait correctement.

    fun generateRole(): List<Role> {
        val roles = mutableListOf<Role>()
        val roleName = listOf("ADMIN", "USER")
        for (name in roleName) {
            val role = Role()
            role.name = name
            roles.add(role)
        }
        return roles
    }

    fun generateUser(count: Int): List<User> {
        val users = mutableListOf<User>()
        val faker = Faker()
        for (i in 1..count) {
            val user = User().apply {
                login = faker.name().username()
                email = faker.internet().emailAddress()
                password = passwordEncoder.encode(faker.internet().password())
                latitude = faker.address().latitude()
                longitude = faker.address().longitude()
            }
            users.add(user)
        }
        return users
    }
}

