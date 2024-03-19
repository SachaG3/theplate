package fr.backend.repository

import fr.backend.models.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(collectionResourceRel = "Message", path = "Message")
interface MessageRepository : JpaRepository<Message, UUID>