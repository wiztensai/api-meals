package com.jetwiz.admin.base

import android.app.Application
import com.jetwiz.admin.ModelBarang
import timber.log.Timber

class BaseApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}