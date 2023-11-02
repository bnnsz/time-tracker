/*
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 *
 */

package me.time.repositories

import me.time.entities.RuningTask
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

/**
 * Running task entity JPA repository interface
 *
 * @author obinna.asuzu
 */
interface RunningTaskEntityRepository : JpaRepository<RuningTask?, Long?> {
    fun findByOrganisationIdAndProjectCodeAndTaskCode(
        organisationId: String?,
        projectCode: String?,
        taskCode: String?
    ): RuningTask?
    fun findAllByTimestampAfter(date: Date?, pageable: Pageable?): Page<RuningTask?>?
}
