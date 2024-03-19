package fr.backend.repository

import fr.backend.models.Guide
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(collectionResourceRel = "guide", path = "guide")
interface GuideRepository : JpaRepository<Guide, UUID>