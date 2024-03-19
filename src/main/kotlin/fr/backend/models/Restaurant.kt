package fr.backend.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.util.*

@Entity
open class Restaurant {
    @Id
    open var id: UUID = UUID.randomUUID()
    open var name: String? = null
    open var address1: String? = null
    open var address2: String? = null
    open var postalCode: String? = null
    open var city: String? = null
    open var coordGPS: String? = null
    open var approuve: String? = null
    open var idTheme: UUID = UUID.randomUUID()

    @ManyToOne
    open var theme: Theme? = null
}