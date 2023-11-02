/*
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 */
package me.time.entities

import jakarta.persistence.*
import java.io.Serializable
import java.util.*

/**
 * Runing Task JPA Entity model
 * @author obinna.asuzu
 */
@Entity
class RuningTask() : BaseEntity(), Serializable {


    @Column
    private val name: String? = null

    @Column
    private val totalTimeLong: Long? = null

    @Column
    private val taskCode: String? = null

    @Column
    private val projectCode: String? = null

    @Column
    private val organisationId: String? = null

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private val timestamp: Date? = null

}
