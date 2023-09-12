package com.bogsnebes.geekapp.di.app_components

import android.content.Context
import com.bogsnebes.geekapp.Application
import com.yariksoffice.lingver.Lingver
import com.yariksoffice.lingver.store.PreferenceLocaleStore
import dagger.Module
import dagger.Provides
import java.util.Locale
import javax.inject.Singleton

@Module
class LocaleModule(private val applicationContext: Context, private val application: Application) {
    private val store = PreferenceLocaleStore(applicationContext, Locale("en"))

    @Provides
    @Singleton
    fun getLingver(): Lingver {
        return Lingver.init(application, store)
    }
}