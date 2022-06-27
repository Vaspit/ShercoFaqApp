package com.example.shercofaqapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.model.repositories.BikeRepository

class GarageFragmentFirebaseViewModel : ViewModel() {

    private val _bikeList = MutableLiveData<List<Bike>>()
    val bikeList : MutableLiveData<List<Bike>>
        get() = _bikeList

    fun createBike(bike: Bike, userId: String, key: String) {
        BikeRepository().createBike(bike, userId, key)
    }

    fun getBikes() {
        BikeRepository().readBikes(_bikeList)
    }

    fun updateBike(bike: Bike, bikeKey: String) {
        BikeRepository().updateBike(bike, bikeKey)
    }

    fun deleteBike(bike: Bike) {
        BikeRepository().deleteBike(bike.bikeFirebaseKey!!)
    }

}