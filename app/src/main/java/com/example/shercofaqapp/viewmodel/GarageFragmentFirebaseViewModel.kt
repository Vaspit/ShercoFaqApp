package com.example.shercofaqapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.model.BikeFirebase
import com.example.shercofaqapp.model.repositories.BikeFireBaseRepository

class GarageFragmentFirebaseViewModel : ViewModel() {

    private val _bikeList = MutableLiveData<List<BikeFirebase>>()
    val bikeList : MutableLiveData<List<BikeFirebase>>
        get() = _bikeList

    fun createBike(bike: BikeFirebase, userId: String, key: String) {
        BikeFireBaseRepository().createBike(bike, userId, key)
    }

    fun getBikes() {
        BikeFireBaseRepository().readBikes(_bikeList)
    }

    fun updateBike(bike: BikeFirebase, bikeKey: String) {
        BikeFireBaseRepository().updateBike(bike, bikeKey)
    }

    fun deleteBike(bike: BikeFirebase) {
        BikeFireBaseRepository().deleteBike(bike.bikeFirebaseKey!!)
    }

}