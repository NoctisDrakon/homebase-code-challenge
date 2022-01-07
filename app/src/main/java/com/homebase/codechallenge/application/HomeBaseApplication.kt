package com.homebase.codechallenge.application

import androidx.multidex.MultiDexApplication
import com.homebase.codechallenge.BuildConfig
import timber.log.Timber

class HomeBaseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}