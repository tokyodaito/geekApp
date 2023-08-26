package com.bogsnebes.geekapp

import android.app.Application
import com.bogsnebes.geekapp.di.AppComponent
import com.bogsnebes.geekapp.di.DaggerAppComponent

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
        appComponent.getRetrofit()
    }

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }
}