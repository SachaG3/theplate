package fr.backend.repository

import fr.backend.models.Evaluation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(collectionResourceRel = "evaluation", path = "evaluation")
interface EvaluationRepository : JpaRepository<Evaluation, UUID>