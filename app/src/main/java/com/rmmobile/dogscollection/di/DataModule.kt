package com.rmmobile.dogscollection.di

import com.rmmobile.dogscollection.data.repository.HomeRepository
import com.rmmobile.dogscollection.data.source.network.HomeDataSourceImpl
import com.rmmobile.dogscollection.data.source.network.api.DogsApi
import com.rmmobile.dogscollection.data.source.network.datasource.DogsDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideHomeDataSource(api: DogsApi): DogsDataSource {
        return HomeDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun provideHomeRepository(dogsDataSource: DogsDataSource): HomeRepository {
        return HomeRepository(dogsDataSource)
    }
}