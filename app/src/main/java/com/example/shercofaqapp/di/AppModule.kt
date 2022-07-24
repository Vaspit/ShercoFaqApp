package com.example.shercofaqapp.di

import com.example.shercofaqapp.model.Bike
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideBike(): Bike {
        return Bike()
    }



}