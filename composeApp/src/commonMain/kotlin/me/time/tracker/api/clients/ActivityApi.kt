/*
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 *
 */

package me.time.tracker.api.clients

import java.util.*

/**
 * The `ActivityApi` interface defines methods for posting user activities and screenshots
 * to a remote API. Implementing classes should provide concrete implementations of these methods.
 * @author Obinna Asuzu
 * @since 2023
 */
interface ActivityApi {
    /**
     * A data class representing user activity information, including various attributes.
     *
     * @param app         The name of the application associated with the activity.
     * @param webTitle    The title of a web page or content related to the activity.
     * @param webUrl      The URL of the web page or content associated with the activity.
     * @param taskCode    The code for the task the user was working on during the activity.
     * @param projectCode The code for the project the user was working on during the activity.
     * @param idle        A flag indicating whether the user was idle during the activity.
     * @param timestamp   The timestamp when the activity occurred.
     */
    data class ActivityDto(
        val app: String?,
        val webTitle: String?,
        val webUrl: String?,
        val taskCode: String?,
        val projectCode: String?,
        val idle: Boolean?,
        val timestamp: Date?
    )

    /**
     * Posts a list of screenshots to the remote API.
     *
     * @param screenshots The list of screenshots as byte arrays to be posted.
     */
    suspend fun postScreenshot(screenshots: List<ByteArray>)

    /**
     * Posts a list of user activities to the remote API.
     *
     * @param activities The list of user activities to be posted.
     */
    suspend fun postActivity(activities: List<ActivityDto>)
}
