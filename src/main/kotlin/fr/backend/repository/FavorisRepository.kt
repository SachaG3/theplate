package fr.backend.repository

import fr.backend.models.Favoris
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(collectionResourceRel = "favoris", path = "favoris")
interface FavorisRepository : JpaRepository<Favoris, UUID>