package fr.backend.repository

import fr.backend.models.Ticket
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.CrossOrigin
import java.util.*

@RepositoryRestResource(collectionResourceRel = "ticket", path = "ticket")
@CrossOrigin(origins = ["*"])
interface TicketRepository : JpaRepository<Ticket, UUID>