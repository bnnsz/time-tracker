/*
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 */
package me.time.tracker.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import me.time.common.entities.BaseEntity
import java.io.Serializable
import java.util.*

/**
 * History JPA Entity model
 * @author obinna.asuzu
 */
@Entity
class History() : BaseEntity(), Serializable {

    @Column
    val app: String? = null

    @Column
    val webTitle: String? = null

    @Column
    val webUrl: String? = null

    @Column
    val taskCode: String? = null

    @Column
    val projectCode: String? = null

    @Column
    val idle: Boolean? = null

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    val timestamp: Date? = null
}
