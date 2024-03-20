package fr.backend.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.util.*

@Entity
open class Plat {
    @Id
    open var id: UUID = UUID.randomUUID()
    open var name: String? = null
    open var image: String? = null
    @Column(columnDefinition = "TEXT")
    open var description: String? = null
    open var price: Int? = null
    open var actif: Boolean? = false
    open var duJour: Boolean? = false

    @ManyToOne
    open var typePlat: TypePlat? = null

    @ManyToOne
    open var restaurant: Restaurant? = null
}