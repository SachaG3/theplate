package fr.backend.repository

import fr.backend.models.Plat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(collectionResourceRel = "plat", path = "plat")
interface PlatRepository : JpaRepository<Plat, UUID>