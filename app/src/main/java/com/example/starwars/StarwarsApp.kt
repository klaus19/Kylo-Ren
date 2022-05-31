package com.example.starwars

import android.app.Application
import androidx.multidex.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class StarwarsApp:Application() {

    override fun onCreate() {
        super.onCreate()
        createTimber()
    }

    private fun createTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}

