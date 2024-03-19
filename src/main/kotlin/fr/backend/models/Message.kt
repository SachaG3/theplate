package fr.backend.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.util.*

@Entity
class Message {
    @Id
    open var id: UUID = UUID.randomUUID()
    open var content: String? = null

    @ManyToOne
    open var user: User? = null

    @ManyToOne
    open var ticket: Ticket? = null
}