package com.example.shercofaqapp.utils

import com.example.shercofaqapp.model.Bike

class CurrentBikeAddress(
    private val bikeList: List<Bike>,
    private val currentBikeIndex: Int) {

    fun getCurrentBikeAddress(
    ): String {
        val currentBikeAddress =
            bikeList[currentBikeIndex].bikeModelYear +
                    bikeList[currentBikeIndex].bikeType +
                    bikeList[currentBikeIndex].bikeEngineType +
                    bikeList[currentBikeIndex].bikeEngineVolume +
                    bikeList[currentBikeIndex].bikeEdition

        return currentBikeAddress.trim()
    }

}