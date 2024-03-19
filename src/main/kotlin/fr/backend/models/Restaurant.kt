package fr.backend.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.util.*

@Entity
open class Restaurant {
    /**
     *CREATE TABLE restaurant(
     *    id INT AUTO_INCREMENT,
     *    nom VARCHAR(100),
     *    description TEXT,
     *    adresse1 TEXT,
     *    adresse2 VARCHAR(50),
     *    cp VARCHAR(5),
     *    ville VARCHAR(100),
     *    coordGPS VARCHAR(10),
     *    approuve BOOLEAN,
     *    idTheme INT NOT NULL,
     *    PRIMARY KEY(id),
     *    FOREIGN KEY(idTheme) REFERENCES Theme(id)
     * );
     */
    @Id
    open var id: UUID = UUID.randomUUID()
    open var name: String? = null
    open var address1: String? = null
    open var address2: String? = null
    open var postalCode: String? = null
    open var city: String? = null
    open var coordGPS: String? = null
    open var approuve: String? = null
    open var idTheme: UUID = UUID.randomUUID()

    @ManyToOne
    open var theme: Theme? = null
}