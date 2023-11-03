/*
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 *
 */

package me.time.repositories

import me.time.hr.entities.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

/**
 * Project entity JPA repository interface
 * @author obinna.asuzu
 */
interface ProjectRepository : JpaRepository<Project?, Long?> {

    @Modifying
    @Query("""
        DELETE FROM Project p
        WHERE p.code NOT IN :codes
    """)
    fun deleteByCodeNotIn(@Param("codes") codes: List<String?>?)

    @Query("select p from Project p where upper(p.code) = upper(:code)")
    fun findByCode(@Param("code") code: String): Project?

}
