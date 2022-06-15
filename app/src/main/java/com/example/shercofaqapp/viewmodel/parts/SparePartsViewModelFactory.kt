package com.example.shercofaqapp.viewmodel.parts

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SparePartsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SparePartsViewModel::class.java)) {
            return SparePartsViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}