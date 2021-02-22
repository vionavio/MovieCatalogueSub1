package com.viona.moviecatalogue.utils

import android.app.Application
import com.viona.moviecatalogue.injection.appModules
import com.viona.moviecatalogue.injection.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppContext : Application() {
    override fun onCreate() {
        super.onCreate()
        startInjection()
    }

    private fun startInjection() {
        val moduleList = listOf(appModules, viewModelModules)

        startKoin {
            androidContext(this@AppContext)
            modules(moduleList)
        }
    }
}