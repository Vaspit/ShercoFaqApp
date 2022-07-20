package com.example.shercofaqapp.di

import android.content.Context
import com.example.shercofaqapp.model.repositories.BikeRepository
import com.example.shercofaqapp.model.repositories.FaqRepository
import com.example.shercofaqapp.model.repositories.TorquesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideBikeRepository() : BikeRepository {
        return BikeRepository()
    }

    @Singleton
    @Provides
    fun provideFaqRepository(
        @ApplicationContext context: Context
    ) : FaqRepository {
        return FaqRepository(context)
    }

    @Singleton
    @Provides
    fun provideTorquesRepository(
        @ApplicationContext context: Context
    ) : TorquesRepository {
        return TorquesRepository(context)
    }
}