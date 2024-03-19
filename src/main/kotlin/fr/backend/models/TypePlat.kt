package fr.backend.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
class TypePlat {
    /**
     *
     * CREATE TABLE TypePlat(
     *    id INT AUTO_INCREMENT,
     *    libelle VARCHAR(60),
     *    PRIMARY KEY(id)
     * );
     *
     */
    @Id
    open var id: UUID = UUID.randomUUID()
    open var libelle: String? = null

}

