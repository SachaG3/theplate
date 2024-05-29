package fr.backend.repository

import fr.backend.models.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.CrossOrigin
import java.util.*

@RepositoryRestResource(collectionResourceRel = "Message", path = "Message")
@CrossOrigin(origins = ["*"])
interface MessageRepository : JpaRepository<Message, UUID> {
    fun findByTicketId(ticketId: UUID): List<Message>
}