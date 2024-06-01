package fr.backend.repository

import fr.backend.models.Plat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.CrossOrigin
import java.util.*

@RepositoryRestResource(collectionResourceRel = "plat", path = "plat")
@CrossOrigin(origins = ["*"])
interface PlatRepository : JpaRepository<Plat, UUID> {
    @CrossOrigin(origins = ["*"])
    fun findByRestaurantId(restaurantId: UUID): List<Plat>
}