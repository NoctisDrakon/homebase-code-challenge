package com.homebase.core.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

open class BaseViewModel : ViewModel() {
    protected val vmScope = CoroutineScope(Dispatchers.IO)
}