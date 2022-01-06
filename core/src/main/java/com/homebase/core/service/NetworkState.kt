package com.homebase.core.service

sealed class NetworkState {

    data class Success(val data: Any?) : NetworkState() {
        inline fun <reified T> responseTo() = data as T
    }

    data class Error(val error: String?) : NetworkState()

}
