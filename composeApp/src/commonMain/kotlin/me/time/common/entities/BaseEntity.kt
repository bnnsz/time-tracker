/*
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 */
package me.time.common.entities
/**
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 */
import jakarta.persistence.*
import java.util.*

/**
 * Base JPA Entity model
 * @author obinna.asuzu
 */
@MappedSuperclass
open class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Long? = null
    @Column(name = "REF")
    var ref : String? = null
    @Column(name = "DESCRIPTION")
    val description : String? = null
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED")
    var created : Date? = null
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED")
    var updated : Date? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BaseEntity) return false
        if (id != other.id) return false
        if (ref != other.ref) return false
        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (ref?.hashCode() ?: 0)
        return result
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
