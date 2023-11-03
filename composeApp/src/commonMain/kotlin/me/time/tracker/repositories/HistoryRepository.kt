/*
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 *
 */
package me.time.tracker.repositories

import me.time.tracker.entities.History
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

/**
 * History entity JPA repository interface
 *
 * @author obinna.asuzu
 */
interface HistoryRepository : JpaRepository<History, Long> {
    @Modifying
    @Query("DELETE History e WHERE e.idle = :idle AND e.timestamp >= :from")
    fun deleteIdleByTimestampGte(@Param("idle") idle: Boolean, @Param("from") from: Date?)
}
