/*
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 *
 */

package me.time.repositories

import me.time.entities.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * User entity JPA repository interface
 * @author obinna.asuzu
 */
interface UserRepository : JpaRepository<User?, Long?>
