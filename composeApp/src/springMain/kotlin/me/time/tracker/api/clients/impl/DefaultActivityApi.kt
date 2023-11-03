/*
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 *
 */

package me.time.tracker.api.clients.impl

import me.time.common.api.ApiClient
import me.time.hr.repositories.UserRepository
import me.time.tracker.api.clients.ActivityApi
import org.springframework.beans.factory.annotation.Value

/**
 * The `DefaultActivityApi` class is an implementation of the `ActivityApi` interface
 * that provides methods for posting user activities and screenshots to a remote API.
 * It extends the `ApiClient` class to handle HTTP requests.
 *
 * @author Obinna Asuzu
 * @since 2023
 */
class DefaultActivityApi(
        val userRepository: UserRepository,
        @Value("\${url.path.activity}")
        val activityApi: String?,
        @Value("\${url.path.screenshot}")
        val screenshotApi: String?): ActivityApi, ApiClient() {

    /**
     * Posts a list of screenshots to the remote API.
     *
     * @param screenshots The list of screenshots as byte arrays to be posted.
     * @throws RuntimeException If the user is not found or the access token is missing.
     */
    override suspend fun postScreenshot(screenshots: List<ByteArray>) {
        val user = userRepository.findAll().firstOrNull()?: throw RuntimeException("User not found")
        val authToken = user.accessToken ?: throw RuntimeException("User not found")
        val header = mapOf("Authentication" to """Bearer $authToken""","extension" to "png")
        screenshotApi?.let {
            postWithMultipart<Unit>(it, ApiOptions(body = screenshots, headers = header))
        }
    }

    /**
     * Posts a list of user activities to the remote API.
     *
     * @param activities The list of user activities to be posted.
     * @throws RuntimeException If the user is not found or the access token is missing.
     */
    override suspend fun postActivity(activities: List<ActivityApi.ActivityDto>) {
        val user = userRepository.findAll().firstOrNull()?: throw RuntimeException("User not found")
        val authToken = user.accessToken ?: throw RuntimeException("User not found")
        val header = mapOf("Authentication" to """Bearer $authToken""")

        activityApi?.let {
            postWithJson<Unit>(it, ApiOptions(body = activities, headers = header))
        }
    }
}