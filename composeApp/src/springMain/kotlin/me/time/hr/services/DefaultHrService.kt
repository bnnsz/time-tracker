/*
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 *  
 */

package me.time.hr.services

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.time.hr.api.clients.HrApi
import me.time.hr.entities.Project
import me.time.hr.entities.Task
import me.time.hr.entities.User
import me.time.hr.repositories.UserRepository
import me.time.repositories.ProjectRepository
import me.time.repositories.TaskRepository
import org.springframework.stereotype.Service

@Service
class DefaultHrService(
        val hrApi: HrApi,
        val taskRepository: TaskRepository,
        val userRepository: UserRepository,
        val projectRepository: ProjectRepository
) : HrService {
    val objectMapper: ObjectMapper = ObjectMapper()
    override suspend fun authenticate(username: String, password: String): User {
        val dto = hrApi.authenticate(username, password) ?: throw RuntimeException("User not found")
        val entity = objectMapper.convertValue(dto, User::class.java)
        return withContext(Dispatchers.IO) {
            userRepository.save(entity)
        }
    }

    override suspend fun getUser(): User {
        val user = userRepository.findAll().firstOrNull() ?: throw RuntimeException("User not found")
        val accessToken = user.accessToken ?: throw RuntimeException("User not found")
        val dto =  hrApi.getUser(accessToken)?: throw RuntimeException("User not found")
        val entity = objectMapper.convertValue(dto, User::class.java)
        return withContext(Dispatchers.IO) {
            userRepository.save(entity)
        }
    }

    override suspend fun getProjects(): List<Project> {
        val user = getUser()
        val authToken = user.accessToken ?: return emptyList()
        val projectDtos = hrApi.getProjects(authToken) ?: return emptyList()

        withContext(Dispatchers.IO) {
            projectRepository.deleteByCodeNotIn(projectDtos.map { it.code })
        }
        val projects = projectDtos.map { dto ->
            projectRepository.findByCode(dto.code) ?: objectMapper.convertValue(dto, Project::class.java)
        }
        return projectRepository.saveAll(projects)
    }

    override suspend fun getTasks(projectCode: String): List<Task> {
        val user = getUser()
        val authToken = user.accessToken ?: return emptyList()
        val taskDtos = hrApi.getTasks(authToken, projectCode) ?: return emptyList()

        withContext(Dispatchers.IO) {
            taskRepository.deleteByCodeNotIn(taskDtos.map { it.code })
        }
        val tasks = taskDtos.map { dto ->
            taskRepository.findByCode(dto.code) ?: objectMapper.convertValue(dto, Task::class.java)
        }
        return taskRepository.saveAll(tasks)
    }
}