package fr.backend.repository

import fr.backend.models.EvalOfficielle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.CrossOrigin
import java.util.*

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "evalofficielle", path = "evalofficielle")
interface EvalOfficielleRepository : JpaRepository<EvalOfficielle, UUID>