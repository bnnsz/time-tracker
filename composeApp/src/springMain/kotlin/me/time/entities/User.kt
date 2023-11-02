/*
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 */
package me.time.entities

import jakarta.persistence.*
import java.io.Serializable

/**
 * User JPA Entity model
 * @author obinna.asuzu
 */
@Entity
class User() : BaseEntity(), Serializable {

    @Column(columnDefinition = "TEXT")
    private var pic: String? = null

    @Column(columnDefinition = "BLOB")
    private val picData: ByteArray? = null

    @Column
    private var username: String? = null

    @Column
    private var pin: String? = null

    @Column
    private var email: String? = null

    @Column
    private var firstName: String? = null

    @Column
    private var lastName: String? = null

    @Column
    private var otherNames: String? = null

    @Column(columnDefinition = "TEXT")
    private var token: String? = null

}
