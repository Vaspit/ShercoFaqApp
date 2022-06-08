package com.example.shercofaqapp.viewmodel.parts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shercofaqapp.model.SparePart

class SparePartViewModelFactory(private val sparePart: SparePart) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SparePartsViewModel::class.java)) {
            return SparePartViewModel(sparePart) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}