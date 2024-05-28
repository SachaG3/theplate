package fr.backend.repository

import fr.backend.models.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.CrossOrigin
import java.util.*

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "Message", path = "Message")
interface MessageRepository : JpaRepository<Message, UUID> {
    @CrossOrigin(origins = ["*"])
    fun findByTicketId(ticketId: UUID): List<Message>
}