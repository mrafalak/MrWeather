package com.mrapps.mrweather

import android.app.Application
import com.mrapps.mrweather.data.di.networkModule
import com.mrapps.mrweather.data.di.repositoryModule
import com.mrapps.mrweather.ui.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(repositoryModule, networkModule, homeModule))
        }
    }
}