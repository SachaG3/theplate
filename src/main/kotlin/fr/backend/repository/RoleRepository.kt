package fr.backend.repository

import fr.backend.models.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource
import org.springframework.web.bind.annotation.CrossOrigin
import java.util.*

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "roles", path = "roles")
interface RoleRepository : JpaRepository<Role, UUID> {
    @RestResource(path = "byName")
    fun findByName(name: String): Role?
}
