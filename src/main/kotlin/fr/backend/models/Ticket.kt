package fr.backend.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.util.*

@Entity
class Ticket {
    @Id
    open var id: UUID = UUID.randomUUID()
    open var title: String? = null
    open var status: Int? = null
    open var description: String? = null

    @ManyToOne
    open var user: User? = null

}