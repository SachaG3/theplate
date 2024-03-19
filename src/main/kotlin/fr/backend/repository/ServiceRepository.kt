package fr.backend.repository

import fr.backend.models.Service
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(collectionResourceRel = "service", path = "service")
interface ServiceRepository : JpaRepository<Service, UUID>