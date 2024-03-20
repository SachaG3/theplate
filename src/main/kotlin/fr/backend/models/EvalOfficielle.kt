package fr.backend.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.util.*

@Entity
class EvalOfficielle {

    @ManyToOne
    @Id
    open var Guide: Guide? = null

    @ManyToOne
    @Id
    open var Restaurant: Restaurant? = null

    @Column(columnDefinition = "TINYINT(4)")
    open var note: Int? = null

    @Column(columnDefinition = "TEXT")
    open var comment: String? = null
    open var dateC: Date? = null

}