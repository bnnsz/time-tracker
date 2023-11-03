/*
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 *
 */

package me.time.hr.services

import me.time.hr.entities.Project
import me.time.hr.entities.Task
import me.time.hr.entities.User

interface HrService {

    suspend fun authenticate(username: String, password: String): User

    suspend fun getUser(): User

    /**
     * Get the list of projects the employee is assigned to
     */
    suspend fun getProjects() : List<Project>

    /**
     * Get the list of tasks the employee is assigned to in a project
     */
    suspend fun getTasks(projectCode: String) : List<Task>
}