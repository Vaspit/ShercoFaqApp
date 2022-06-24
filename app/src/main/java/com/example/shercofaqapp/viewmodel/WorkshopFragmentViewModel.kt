package com.example.shercofaqapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.model.BikeFirebase
import com.example.shercofaqapp.utils.CurrentBikeAddress

class WorkshopFragmentViewModel : ViewModel() {

    /** Bike names */
    private val _bike = MutableLiveData<BikeFirebase>()
    val bike : LiveData<BikeFirebase>
        get() = _bike

    /** Bike address */
    private val _bikeAddress = MutableLiveData<String>()
    val bikeAddress : LiveData<String>
        get() = _bikeAddress

    fun setBike(bikeFromGarage: BikeFirebase) {
        _bike.value = bikeFromGarage
    }

    fun getBikeAddress(bike: BikeFirebase) {
        _bikeAddress.value = CurrentBikeAddress().getCurrentBikeAddress(bike)
    }

}