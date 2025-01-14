package fr.backend.repository

import fr.backend.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource
import org.springframework.web.bind.annotation.CrossOrigin
import java.util.*


@RepositoryRestResource(collectionResourceRel = "users", path = "users")
@CrossOrigin(origins = ["*"])
interface UserRepository : JpaRepository<User, UUID> {
    @RestResource(path = "bylogin")
    fun findByLogin(login: String): User?
    fun findByEmail(email: String): User?

    @Query("SELECT u FROM User u WHERE u.login = :loginOrEmail OR u.email = :loginOrEmail")
    fun findByLoginOrEmail(loginOrEmail: String): Optional<User>

    @RestResource(path = "byid")
    override fun findById(id: UUID): Optional<User>


}
