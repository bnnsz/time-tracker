/*
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 *
 */

package me.time.tracker.repositories

import me.time.common.entities.Setting
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Setting entity JPA repository interface
 * @author obinna.asuzu
 */
interface SettingRepository : JpaRepository<Setting, String>
