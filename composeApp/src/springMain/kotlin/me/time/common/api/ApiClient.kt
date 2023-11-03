/*
 * Copyright (c) 2023 Obinna Asuzu
 * This file is subject to the terms and conditions of the MIT License.
 * See the file "LICENSE" for details.
 *
 */

package me.time.common.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.util.UUID.randomUUID

/**
 * The `ApiClient` class is an abstract class for making HTTP requests to remote APIs.
 * It provides methods for performing HTTP GET and POST requests with JSON and multipart
 * form data payloads. The class is designed to be extended by specific API client
 * implementations.
 *
 * @author Obinna Asuzu
 * @since 2023
 */
abstract class ApiClient {
    /**
     * A data structure representing options for making API requests, including headers,
     * query parameters, and the request body.
     */
    data class ApiOptions(
            val headers: Map<String, String> = emptyMap(),
            val queryParameters: Map<String, String> = emptyMap(),
            val body: Any? = null
    )


    protected val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        engine {
            requestTimeout = 30_000
        }
    }

    /**
     * Makes an HTTP POST request with a JSON payload and returns the response as an object
     * of the specified type.
     *
     * @param <T>      The type of the expected response object.
     * @param url      The URL to send the POST request to.
     * @param options  The options for the request, including headers, query parameters, and body.
     * @return The response object of type T or null if the request was unsuccessful.
     */
    protected suspend inline fun <reified T> postWithJson(
            url: String, options: ApiOptions = ApiOptions()): T? {
        val response = client.post(url) {
            applyJsonOptions(options)
        }
        return handleResponse<T>(response)
    }

    /**
     * Makes an HTTP GET request and returns the response as an object of the specified type.
     *
     * @param <T>      The type of the expected response object.
     * @param url      The URL to send the GET request to.
     * @param options  The options for the request, including headers and query parameters.
     * @return The response object of type T or null if the request was unsuccessful.
     */
    protected suspend inline fun <reified T> getWithJson(
            url: String, options: ApiOptions = ApiOptions()): T? {
        val response = client.get(url) {
            applyJsonOptions(options)
        }
        return handleResponse<T>(response)
    }

    /**
     * Makes an HTTP POST request with multipart form data and returns the response as an object
     * of the specified type.
     *
     * @param <T>      The type of the expected response object.
     * @param url      The URL to send the POST request to.
     * @param options  The options for the request, including headers, query parameters, and body.
     * @return The response object of type T or null if the request was unsuccessful.
     */
    protected suspend inline fun <reified T> postWithMultipart(
            url: String, options: ApiOptions = ApiOptions()): T? {
        val response = client.post(url) {
            applyMultipartOptions(options)
        }
        return handleResponse<T>(response)
    }

    /**
     * Applies the specified options to an HTTP request builder for a JSON request.
     *
     * @param options  The options for the request, including headers, query parameters, and body.
     * @return The Unit for the builder.
     */
    protected fun HttpRequestBuilder.applyJsonOptions(options: ApiOptions) {
        contentType(ContentType.Application.Json)
        options.headers.forEach { (key, value) ->
            header(key, value)
        }
        options.queryParameters.forEach { (key, value) ->
            parameter(key, value)
        }
        options.body?.let {
            setBody(it)
        }
    }

    /**
     * Applies the specified options to an HTTP request builder for a multipart form data request.
     *
     * @param options  The options for the request, including headers, query parameters, and body.
     * @return The Unit for the builder.
     */
    protected fun HttpRequestBuilder.applyMultipartOptions(options: ApiOptions) {
        contentType(ContentType.MultiPart.FormData)
        val mutableHeaders = options.headers.toMutableMap()
        mutableHeaders.remove("extension")
        mutableHeaders.forEach { (key, value) ->
            header(key, value)
        }
        options.queryParameters.forEach { (key, value) ->
            parameter(key, value)
        }
        options.body?.let {
            setBody(it)
        }
        options.body?.let { body ->
            val data: List<ByteArray> = ArrayList<ByteArray>().apply {
                when (body) {
                    is ByteArray -> {
                        add(body)
                    }

                    is List<*> -> {
                        body.forEach {
                            if (it is ByteArray) {
                                add(it)
                            }
                        }
                    }
                }
            }

            if (data.isEmpty()) {
                throw RuntimeException("No data to upload")
            }

            val sdf = SimpleDateFormat("ddMMyyyyhhmmssSSS")
            val boundary = randomUUID()
            val fileExtension = options.headers["extension"] ?: "png"
            val formData = formData {
                data.forEachIndexed { index, bytes ->
                    val fileName = "display${index + 1}_${sdf.format(System.currentTimeMillis())}.${fileExtension}"
                    append(HttpHeaders.ContentDisposition, "form-data; name=\"file\"; filename=\"$fileName\"")
                    append(HttpHeaders.ContentType, """multipart/form-data; boundary=${boundary}""")
                    setBody(bytes)
                }
            }
            setBody(formData)
        }
    }


    /**
     * Handles the HTTP response and returns the response body as an object of the specified type.
     *
     * @param <T>      The type of the expected response object.
     * @param response The HTTP response to handle.
     * @return The response object of type T if the status code is OK; otherwise, it throws an exception.
     */
    protected suspend inline fun <reified T> handleResponse(response: HttpResponse): T? {
        return if (response.status == HttpStatusCode.OK) {
            response.body<T?>()
        } else {
            throw RuntimeException("Error: ${response.status}")
        }
    }
}