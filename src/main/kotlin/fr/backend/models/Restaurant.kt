package fr.backend.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
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
    @NotBlank
    open var address1: String? = null
    @NotBlank
    open var address2: String? = null
    @NotBlank
    open var postalCode: String? = null
    @NotBlank
    open var city: String? = null

    @Pattern(regexp = "[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?)", message = "Latitude must be a valid GPS coordinate")
    @NotEmpty
    open var latitude: String? = null

    @Pattern(regexp = "[-+]?((1[0-7]|[1-9])?\\d(\\.\\d+)?|180(\\.0+)?)", message = "Longitude must be a valid GPS coordinate")
    @NotEmpty
    open var longitude: String? = null

    open var approuve: Boolean = false
    open var idTheme: UUID = UUID.randomUUID()

    @ManyToOne
    open var theme: Theme? = null
}