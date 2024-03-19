package fr.backend.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
class EvalOfficielle {
    @ManyToOne
    @Id
    open var Guide: Guide? = null

    @ManyToOne
    @Id
    open var Restaurant: Restaurant? = null
    open var note: String? = null
    open var comment: String? = null
    open var dateC: String? = null

}