/*
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 */
package me.time.entities

import jakarta.persistence.*
import java.io.Serializable

/**
 * Task JPA Entity model
 * @author obinna.asuzu
 */
@Entity
class Task() : BaseEntity(), Serializable {

    @Column
    private var code: String? = null

    @Column
    private var name: String? = null

    @Column
    private var projectCode: String? = null

    @Column
    private var organisationId: String? = null

}
