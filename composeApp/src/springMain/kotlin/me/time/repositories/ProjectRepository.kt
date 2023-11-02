/*
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 *
 */

package me.time.repositories

import me.time.entities.Project
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Project entity JPA repository interface
 * @author obinna.asuzu
 */
interface ProjectRepository : JpaRepository<Project?, Long?> {
    fun findByOrganisationId(organisationId: String?): List<Project?>?
}
