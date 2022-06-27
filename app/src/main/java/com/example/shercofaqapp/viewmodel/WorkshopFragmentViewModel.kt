package com.example.shercofaqapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.model.Bike

class WorkshopFragmentViewModel : ViewModel() {

    /** Bikes*/
    private val _bike = MutableLiveData<Bike>()
    val bike : LiveData<Bike>
        get() = _bike

    fun setBike(bikeFromGarage: Bike) {
        _bike.value = bikeFromGarage
    }

}