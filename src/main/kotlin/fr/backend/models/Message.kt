package fr.backend.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.NotBlank
import java.util.*

@Entity
class Message {
    @Id
    open var id: UUID = UUID.randomUUID()
    @Column(columnDefinition = "TEXT")
    @NotBlank
    open var contenu: String? = null

    @ManyToOne
    open var user: User? = null

    @ManyToOne
    open var ticket: Ticket? = null
}