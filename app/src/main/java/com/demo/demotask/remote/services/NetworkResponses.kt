package com.demo.demotask.remote.services

sealed class NetworkResponses<T> (
    data: T? = null,
    exception: Exception? = null
) {
    data class Success <T>(val data: T) : NetworkResponses<T>(data, null)

    data class Error <T>(
        val exception: Exception
    ) : NetworkResponses<T>(null, exception)

}