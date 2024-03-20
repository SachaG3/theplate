package fr.backend.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.util.*

@Entity
class Ticket {
    @Id
    open var id: UUID = UUID.randomUUID()
    open var title: String? = null
    @Column(columnDefinition = "TINYINT(2)")
    open var statut: Int? = null
    @Column(columnDefinition = "TEXT")
    open var description: String? = null

    @ManyToOne
    open var user: User? = null

}