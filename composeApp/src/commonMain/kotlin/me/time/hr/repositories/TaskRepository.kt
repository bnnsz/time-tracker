/*
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 *
 */

package me.time.repositories

import me.time.hr.entities.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

/**
 * Task entity JPA repository interface
 * @author obinna.asuzu
 */
interface TaskRepository : JpaRepository<Task, Long?> {
    @Modifying
    @Query("""
        DELETE FROM Task t
        WHERE t.code NOT IN :codes
    """)
    fun deleteByCodeNotIn(@Param("codes") codes: List<String>)

    @Query("select t from Task t where upper(t.code) = upper(:code)")
    fun findByCode(@Param("code") code: String): Task?
}
