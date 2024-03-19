package fr.backend.repository

import fr.backend.models.TypePlat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(collectionResourceRel = "typeplat", path = "typeplat")
interface TypePlatRepository : JpaRepository<TypePlat, UUID>