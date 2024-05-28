package fr.backend.repository

import fr.backend.models.TypePlat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.CrossOrigin
import java.util.*

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "typeplat", path = "typeplat")
interface TypePlatRepository : JpaRepository<TypePlat, UUID>