package com.farouk.binance.data.di

import com.farouk.binance.data.local.AppDatabase
import com.farouk.binance.data.local.DelivererDao
import com.farouk.binance.data.local.RemoteKeyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
// curl -X GET "https://onta.dz/api/deliverers/reg1/New%20Supplier?page=2&per_page=2"
@InstallIn(SingletonComponent::class)
@Module
object DelivererModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://onta.dz/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideDelivererDao(appDatabase: AppDatabase): DelivererDao {
        return appDatabase.delivererDao()
    }

    @Singleton
    @Provides
    fun provideRemoteKeyDao(appDatabase: AppDatabase): RemoteKeyDao {
        return appDatabase.getRemoteKeyDao()
    }
}