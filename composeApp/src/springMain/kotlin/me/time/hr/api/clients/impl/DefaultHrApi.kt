/*
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 *
 */

package me.time.hr.api.clients.impl

import kotlinx.serialization.Serializable
import me.time.common.api.ApiClient
import me.time.hr.api.clients.HrApi
import me.time.hr.api.clients.HrApi.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * The `DefaultHrApi` class is an implementation of the `HrApi` interface that provides
 * methods for authenticating users, retrieving user information, projects, and tasks from
 * a remote HR API. It extends the `ApiClient` class to handle HTTP requests.
 * @author Obinna Asuzu
 * @since 2023
 */
@Component
class DefaultHrApi(
        @Value("\${api.auth}")
        val authApi: String?,
        @Value("\${api.users}")
        val userApi: String?,
        @Value("\${api.projects}")
        val projectApi: String?,
        @Value("\${api.tasks}")
        val taskApi: String?) : HrApi, ApiClient() {

    /**
     * A data class representing an authentication request with a username and password.
     *
     * @param username The username for authentication.
     * @param password The password for authentication.
     */
    @Serializable
    data class AuthenticationRequest(val username: String, val password: String)

    /**
     * Authenticates a user using the provided username and password and returns user information.
     *
     * @param username The username for authentication.
     * @param password The password for authentication.
     * @return A UserDto object containing user information if authentication is successful; null otherwise.
     */
    override suspend fun authenticate(username: String, password: String): UserDto? {
        return authApi?.let {
            postWithJson<UserDto>(it, ApiOptions(body = AuthenticationRequest(username, password)))
        }
    }

    /**
     * Retrieves user information using the provided authentication token.
     *
     * @param authToken The authentication token obtained after successful authentication.
     * @return A UserDto object containing user information; null if the user information could not be retrieved.
     */
    override suspend fun getUser(authToken: String): UserDto? {
        return userApi?.let {
            getWithJson<UserDto>(it,
                    ApiOptions(headers = mapOf("Authentication" to """Bearer $authToken""")))
        }
    }

    /**
     * Retrieves a list of projects associated with the authenticated user.
     *
     * @param authToken The authentication token obtained after successful authentication.
     * @return A list of ProjectDto objects representing projects; empty if the projects could not be retrieved.
     */
    override suspend fun getProjects(authToken: String): List<ProjectDto>? {
        return projectApi?.let {
            getWithJson<List<ProjectDto>>(it,
                    ApiOptions(headers = mapOf("Authentication" to """Bearer $authToken""")))
        }
    }

    /**
     * Retrieves a list of tasks associated with the authenticated user and an optional project code.
     *
     * @param authToken    The authentication token obtained after successful authentication.
     * @param projectCode  An optional project code to filter tasks (can be null).
     * @return A list of TaskDto objects representing tasks; empty if the tasks could not be retrieved.
     */
    override suspend fun getTasks(authToken: String, projectCode: String?): List<TaskDto>? {
        return taskApi?.let {
            getWithJson<List<TaskDto>>(it,
                    ApiOptions(headers = mapOf("Authentication" to """Bearer $authToken"""),
                            queryParameters = mapOf("projectCode" to projectCode.orEmpty())))
        }
    }

}