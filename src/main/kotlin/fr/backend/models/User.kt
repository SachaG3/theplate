package fr.backend.models

import jakarta.persistence.*
import java.util.*

@Entity
@Table(indexes = [Index(unique = true, columnList = "login")])
open class User {
    @Id
    open var id: UUID = UUID.randomUUID()

    @Column(nullable = false)
    open var login: String? = null

    @Column(nullable = false)
    open var email: String? = null

    @Column(nullable = false)
    open var password: String? = null

    open var coordGPS: String? = null

    @ManyToOne
    open var role: Role? = null


}