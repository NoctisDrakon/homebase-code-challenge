package com.homebase.codechallenge

import androidx.multidex.MultiDexApplication
import timber.log.Timber

class HomeBaseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}