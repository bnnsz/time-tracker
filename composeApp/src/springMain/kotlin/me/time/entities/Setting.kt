/*
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 */
package me.time.entities

import jakarta.persistence.*
import java.io.Serializable

/**
 * Setting JPA Entity model
 * @author obinna.asuzu
 */
@Entity
class Setting() : BaseEntity(), Serializable {

    @Column
    private val value: String? = null
}
