/*
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 *
 */

package me.time.hr.repositories

import me.time.hr.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * User entity JPA repository interface
 * @author obinna.asuzu
 */
@Repository
interface UserRepository : JpaRepository<User, Long>
