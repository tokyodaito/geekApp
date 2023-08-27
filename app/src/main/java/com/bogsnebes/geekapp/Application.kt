package com.bogsnebes.geekapp

import android.app.Application
import android.content.Context
import androidx.core.app.ActivityCompat
import com.bogsnebes.geekapp.di.AppComponent
import com.bogsnebes.geekapp.di.DaggerAppComponent
import java.util.Locale

class Application : Application() {
    private lateinit var context: Context
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
        context = this.applicationContext
        appComponent.getRetrofit()
    }

    fun changeLanguage(languages: Languages) {
        when (languages) {
            Languages.ENGLISH -> {
                val locale = Locale("en")
                Locale.setDefault(locale)
                context.apply {
                    resources.configuration.setLocale(
                        locale
                    )
                    createConfigurationContext(resources.configuration)
                    ActivityCompat.recreate(appComponent.getMainActivity())
                }
            }

            Languages.GERMAN -> {
                val locale = Locale("ge")
                Locale.setDefault(locale)
                context.apply {
                    resources.configuration.setLocale(
                        locale
                    )
                    createConfigurationContext(resources.configuration)
                    ActivityCompat.recreate(appComponent.getMainActivity())
                }

            }

            Languages.RUSSIAN -> {
                val locale = Locale("ru")
                Locale.setDefault(locale)
                context.apply {
                    resources.configuration.setLocale(
                        locale
                    )
                    createConfigurationContext(resources.configuration)
                    ActivityCompat.recreate(appComponent.getMainActivity())
                }
            }
        }
    }

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }
}