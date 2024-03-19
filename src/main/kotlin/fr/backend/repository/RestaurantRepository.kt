package fr.backend.repository

import fr.backend.models.Restaurant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(collectionResourceRel = "restaurant", path = "restaurant")
interface RestaurantRepository : JpaRepository<Restaurant, UUID>