package fr.backend.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.util.*

@Entity
open class Guide {
    @Id
    open var id: UUID = UUID.randomUUID()
    open var name: String? = null

    @ManyToOne
    open var user: User? = null
}