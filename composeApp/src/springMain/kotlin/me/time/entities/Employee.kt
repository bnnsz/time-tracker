/**
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 */
package me.time.entities;

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Lob
import java.io.Serializable

/**
 * Employee JPA Entity model
 * @author obinna.asuzu
 */
@Entity
class Employee(): BaseEntity(), Serializable {
    @Column(columnDefinition = "TEXT")
    var pic: String? = null;
    @Lob
    var picData: ByteArray? = null;
    @Column
    var email: String? = null;
    @Column
    var firstName: String? = null;
    @Column
    var lastName: String? = null;
    @Column
    var otherNames: String? = null;
    @Column(columnDefinition = "TEXT")
    var token: String? = null;
    @Column
    var organisationId: String? = null;
    @Column
    var organisationName: String? = null
}



































