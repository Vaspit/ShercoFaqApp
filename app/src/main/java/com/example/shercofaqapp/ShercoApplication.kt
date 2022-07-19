package com.example.shercofaqapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShercoApplication  :Application() {

    override fun onCreate() {
        super.onCreate()
    }
}