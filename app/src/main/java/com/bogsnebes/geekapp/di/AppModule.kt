package com.bogsnebes.geekapp.di

import com.bogsnebes.geekapp.model.impl.FactsImpl
import com.bogsnebes.geekapp.ui.screens.main_screen.MainScreen
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun getMainScreen() = MainScreen()

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val okHttpClient = OkHttpClient
            .Builder()
            .callTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun getFactsImpl(): FactsImpl {
        return FactsImpl()
    }

    companion object {
        private const val BASE_URL = "https://uselessfacts.jsph.pl/"
    }
}