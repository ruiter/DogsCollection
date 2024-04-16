package com.rmmobile.dogscollection.di

import com.rmmobile.dogscollection.data.repository.BreedDetailRepository
import com.rmmobile.dogscollection.data.repository.HomeRepository
import com.rmmobile.dogscollection.data.source.network.datasource.DataSourceImpl
import com.rmmobile.dogscollection.data.source.network.api.DogsApi
import com.rmmobile.dogscollection.data.source.network.datasource.DogsDataSource
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
        return DataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun provideHomeRepository(dogsDataSource: DogsDataSource): HomeRepository {
        return HomeRepository(dogsDataSource)
    }

    @Singleton
    @Provides
    fun provideBreedDetailRepository(dataSource: DogsDataSource): BreedDetailRepository {
        return BreedDetailRepository(dataSource)
    }
}