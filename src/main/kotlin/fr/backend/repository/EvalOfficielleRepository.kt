package fr.backend.repository

import fr.backend.models.EvalOfficielle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(collectionResourceRel = "evalofficielle", path = "evalofficielle")
interface EvalOfficielleRepository : JpaRepository<EvalOfficielle, UUID>