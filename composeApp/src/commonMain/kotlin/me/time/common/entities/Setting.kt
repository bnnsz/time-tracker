/*
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 *
 */
package me.time.common.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.io.Serializable

/**
 * Setting JPA Entity model
 * @author obinna.asuzu
 */
@Entity
@Table(name = "TABLE_SETTINGS")
class Setting() : BaseEntity(), Serializable {

    @Column(columnDefinition = "TEXT", name = "SETTING_VALUE")
    private val value: String? = null
}
