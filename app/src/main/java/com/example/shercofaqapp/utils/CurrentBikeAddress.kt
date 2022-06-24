package com.example.shercofaqapp.utils

import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.model.BikeFirebase

class CurrentBikeAddress() {

    fun getCurrentBikeAddress(bike: BikeFirebase): String {
        val currentBikeAddress =
            bike.bikeModelYear +
                    bike.bikeType +
                    bike.bikeEngineType +
                    bike.bikeEngineVolume +
                    bike.bikeEdition

        return currentBikeAddress.trim()
    }

}