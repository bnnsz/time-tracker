/*
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 *
 */

package me.time.hr.api.clients

/**
 * The `HrApi` interface defines a set of methods for interacting with a Human Resources (HR) API.
 * Implementing classes should provide concrete implementations of these methods for authenticating
 * users, retrieving user information, projects, and tasks.
 *
 * @author Obinna Asuzu
 * @since 2023
 */
interface HrApi {
    /**
     * Authenticates a user using a username and password and returns user information.
     *
     * @param username The username for authentication.
     * @param password The password for authentication.
     * @return A UserDto object containing user information if authentication is successful; null otherwise.
     */
    suspend fun authenticate(username: String, password: String): UserDto?
    /**
     * Retrieves user information using the provided authentication token.
     *
     * @param authToken The authentication token obtained after successful authentication.
     * @return A UserDto object containing user information; empty if the user information could not be retrieved.
     */
    suspend fun getUser(authToken: String): UserDto?
    /**
     * Retrieves a list of projects associated with the authenticated user.
     *
     * @param authToken The authentication token obtained after successful authentication.
     * @return A list of ProjectDto objects representing projects; empty if the projects could not be retrieved.
     */
    suspend fun getProjects(authToken: String): List<ProjectDto>?

    /**
     * Retrieves a list of tasks associated with the authenticated user and an optional project code.
     *
     * @param authToken    The authentication token obtained after successful authentication.
     * @param projectCode  An optional project code to filter tasks (can be null).
     * @return A list of TaskDto objects representing tasks; null if the tasks could not be retrieved.
     */
    suspend fun getTasks(authToken: String, projectCode: String?): List<TaskDto>?

    /**
     * A data class representing project information, including a project code, name, description, and tasks.
     *
     * @param code        The code or identifier for the project.
     * @param name        The name of the project.
     * @param description A description of the project.
     * @param tasks       A list of TaskDto objects representing tasks associated with the project.
     */
    data class ProjectDto (
            val code: String,
            val name: String,
            val description: String,
            val tasks: List<TaskDto>
    )

    /**
     * A data class representing task information, including a task code, name, and description.
     *
     * @param code        The code or identifier for the task.
     * @param name        The name of the task.
     * @param description A description of the task.
     */
    data class TaskDto(
            val code: String,
            val name: String,
            val description: String
    )

    /**
     * A data class representing user information, including a username, first name, last name, email,
     * authentication token, and photo.
     *
     * @param username  The username of the user.
     * @param firstName The first name of the user.
     * @param lastName  The last name of the user.
     * @param email     The email address of the user.
     * @param authToken The authentication token for the user.
     * @param photo     The photo of the user.
     */
    data class UserDto(
            val username: String,
            val firstName: String,
            val lastName: String,
            val otherNames: String?,
            val email: String,
            val authToken: String,
            val photo: String?,
    )
}




