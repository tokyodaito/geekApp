package com.bogsnebes.geekapp

import android.app.Application
import com.bogsnebes.geekapp.di.app_components.AppComponent
import com.bogsnebes.geekapp.di.app_components.AppModule
import com.bogsnebes.geekapp.di.app_components.DaggerAppComponent
import com.bogsnebes.geekapp.di.database.DaggerDataBaseComponent
import com.bogsnebes.geekapp.di.database.DataBaseComponent
import com.bogsnebes.geekapp.di.database.DataBaseModule
import com.bogsnebes.geekapp.di.network.DaggerNetworkComponent
import com.bogsnebes.geekapp.di.network.NetworkComponent

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(applicationContext)).build()
        dataBaseComponent =
            DaggerDataBaseComponent.builder().dataBaseModule(DataBaseModule(applicationContext))
                .build()
        networkComponent = DaggerNetworkComponent.builder().build()

        networkComponent.getRetrofit()
        dataBaseComponent.getDatabase()
    }

    companion object {
        lateinit var appComponent: AppComponent
            private set

        lateinit var dataBaseComponent: DataBaseComponent
            private set

        lateinit var networkComponent: NetworkComponent
            private set
    }
}