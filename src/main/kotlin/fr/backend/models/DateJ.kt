package fr.backend.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
open class DateJ {
    /**
     *
     * CREATE TABLE DateJ(
     *    dateJ DATE,
     *    PRIMARY KEY(dateJ)
     * );
     */
    @Id
    open var dateJ: Date? = null
}