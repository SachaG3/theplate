package fr.backend.models

import jakarta.annotation.Nonnull
import jakarta.persistence.*
import jakarta.validation.constraints.Email
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
    open var password: String? = null

    open var latitude: String? = null


    open var longitude: String? = null

    open var enabled: Boolean = false

    @ManyToOne
    @Nonnull
    open lateinit var role: Role


}