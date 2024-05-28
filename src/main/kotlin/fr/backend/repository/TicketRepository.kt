package fr.backend.repository

import fr.backend.models.Ticket
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.CrossOrigin
import java.util.*

@CrossOrigin(origins = ["*"])
@RepositoryRestResource(collectionResourceRel = "ticket", path = "ticket")
interface TicketRepository : JpaRepository<Ticket, UUID> {
    fun findByUserId(userId: UUID): List<Ticket>
}