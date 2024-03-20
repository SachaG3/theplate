package fr.backend.models

import jakarta.annotation.Nonnull
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern
import java.util.*

@Entity
@Table(indexes = [Index(unique = true, columnList = "login")])
open class User {
    @Id
    open var id: UUID = UUID.randomUUID()

    @Column(nullable = false)
    open var login: String? = null

    @Column(nullable = false)
    @Email
    open var email: String? = null

    @Column(nullable = false)
    @Pattern(regexp = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=])(?=\\S+$).{9,}$")
    open var password: String? = null

    @Pattern(regexp = "[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?)", message = "Latitude must be a valid GPS coordinate")
    open var latitude: String? = null

    @Pattern(regexp = "[-+]?((1[0-7]|[1-9])?\\d(\\.\\d+)?|180(\\.0+)?)", message = "Longitude must be a valid GPS coordinate")
    open var longitude: String? = null

    @ManyToOne
    @Nonnull
    open lateinit var role: Role


}