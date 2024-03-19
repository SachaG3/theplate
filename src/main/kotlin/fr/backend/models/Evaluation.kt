package fr.backend.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.util.*

@Entity
open class Evaluation {
    @Id
    open var id: UUID = UUID.randomUUID()
    open var note: String? = null
    open var commentclient: String? = null
    open var commentadmin: String? = null
    open var date: Date? = null

    @ManyToOne
    open var restaurant: Restaurant? = null

    @ManyToOne
    open var user: User? = null
}