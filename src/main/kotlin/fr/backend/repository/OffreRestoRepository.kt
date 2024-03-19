package fr.backend.repository

import fr.backend.models.OffreResto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(collectionResourceRel = "offreresto", path = "offreresto")
interface OffreRestoRepository : JpaRepository<OffreResto, UUID>