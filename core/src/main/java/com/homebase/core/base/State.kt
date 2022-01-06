package com.homebase.core.base

sealed class State {

    object Loading : State()

    data class Success(val data: Any?) : State() {
        @JvmName("getData1")
        inline fun <reified T> getData() = data as T
    }

    data class Error(val error: String) : State()
}
