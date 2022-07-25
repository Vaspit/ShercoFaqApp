package com.example.shercofaqapp.di

import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.model.repositories.BikeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("test_repository")
    fun provideBikeRepository() : BikeRepository {
        return BikeRepository()
    }

    @Provides
    @Named("test_bike")
    fun provideBike() : Bike {
        return Bike()
    }

}