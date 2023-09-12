package com.bogsnebes.geekapp.di.network

import com.bogsnebes.geekapp.model.network.api.FactsApi
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkComponent {
    fun getRetrofit(): Retrofit

    fun getFactsApi(): FactsApi
}