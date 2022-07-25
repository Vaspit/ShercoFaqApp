package com.example.shercofaqapp.model

data class Bike (
    var bikeName: String? = "null",
    var bikeModelYear: String? = "null",
    var bikeType: String? = "null",
    var bikeEngineType: String? = "null",
    var bikeEngineVolume: String? = "null",
    var bikeEdition: String? = "null",
    var bikeImage: String? = "null",
    var bikeFirebaseKey: String? = "null",
        ) {

    fun toMap(): Map<String, Any> {
        val result = HashMap<String, Any>()

        result["bikeName"] = bikeName!!
        result["bikeModelYear"] = bikeModelYear!!
        result["bikeType"] = bikeType!!
        result["bikeEngineType"] = bikeEngineType!!
        result["bikeEngineVolume"] = bikeEngineVolume!!
        result["bikeEdition"] = bikeEdition!!
        result["bikeImage"] = bikeImage!!
        result["bikeFirebaseKey"] = bikeFirebaseKey!!

        return result
    }

}