/*
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 *
 */
package me.time.hr.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import me.time.common.entities.BaseEntity
import java.io.Serializable

/**
 * User JPA Entity model
 * @author obinna.asuzu
 */
@Entity
@Table(name = "TABLE_USERS")
class User() : BaseEntity(), Serializable {

    @Column
    var firstName: String? = null

    @Column
    var lastName: String? = null

    @Column
    var otherNames: String? = null

    @Column
    var username: String? = null

    @Column
    var email: String? = null

    @Column(columnDefinition = "TEXT")
    var photo: String? = null

    @Column(columnDefinition = "TEXT")
    var accessToken: String? = null

}
