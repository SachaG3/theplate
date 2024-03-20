package fr.backend.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.util.*

@Entity
open class Evaluation {
    @Id
    open var id: UUID = UUID.randomUUID()
    @Column(columnDefinition = "TINYINT(4)")
    open var note: Int? = null
    @Column(columnDefinition = "TEXT")
    open var commentclient: String? = null
    open var commentadmin: String? = null
    open var date: Date? = null
    @Column(columnDefinition = "TEXT")
    open var commentReponse: String? = null

    @ManyToOne
    open var restaurant: Restaurant? = null

    @ManyToOne
    open var user: User? = null
}