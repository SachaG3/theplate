package fr.backend.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
open class DateJ {
    @Id
    open var dateJ: Date? = null
}