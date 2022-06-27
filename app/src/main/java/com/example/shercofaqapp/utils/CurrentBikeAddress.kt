package com.example.shercofaqapp.utils

import com.example.shercofaqapp.model.Bike

class CurrentBikeAddress() {

    fun getCurrentBikeAddress(bike: Bike): String {
        val currentBikeAddress =
            bike.bikeModelYear +
                    bike.bikeType +
                    bike.bikeEngineType +
                    bike.bikeEngineVolume +
                    bike.bikeEdition

        return currentBikeAddress.trim()
    }

}