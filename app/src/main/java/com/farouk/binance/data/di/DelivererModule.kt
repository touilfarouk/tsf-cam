package com.farouk.binance.data.di

import com.farouk.binance.data.local.AppDatabase
import com.farouk.binance.data.local.DelivererDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DelivererModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://105.97.88.111/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideDelivererDao(appDatabase: AppDatabase): DelivererDao {
        return appDatabase.delivererDao()
    }
}