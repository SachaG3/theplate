package fr.backend.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.NotBlank
import java.util.*

@Entity
open class Restaurant {
    @Id
    open var id: UUID = UUID.randomUUID()
    @NotBlank
    open var name: String? = null
    @Column(columnDefinition = "TEXT")
    @NotBlank
    open var decription: String? = null
    @Column(columnDefinition = "TEXT")
    open var address1: String? = null
    open var address2: String? = null
    open var postalCode: String? = null
    open var city: String? = null
    open var coordGPS: String? = null
    open var approuve: Boolean = false
    open var idTheme: UUID = UUID.randomUUID()

    @ManyToOne
    open var theme: Theme? = null
}