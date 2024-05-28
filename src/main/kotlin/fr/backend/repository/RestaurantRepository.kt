package fr.backend.repository

import fr.backend.models.Restaurant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.CrossOrigin
import java.util.*

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "restaurant", path = "restaurant")
interface RestaurantRepository : JpaRepository<Restaurant, UUID>