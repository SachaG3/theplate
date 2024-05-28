package fr.backend.repository

import fr.backend.models.Theme
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.CrossOrigin
import java.util.*

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "theme", path = "theme")
interface ThemeRepository : JpaRepository<Theme, UUID>
