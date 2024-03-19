package fr.backend.repository

import fr.backend.models.Preferences
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(collectionResourceRel = "preferences", path = "preferences")
interface PreferencesRepository : JpaRepository<Preferences, UUID>