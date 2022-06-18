package com.example.shercofaqapp.model.repositories

import androidx.lifecycle.MutableLiveData
import com.example.shercofaqapp.model.Bike
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BikeFireBaseRepository {

    fun createBike(bike: Bike, userId: String) {
        lateinit var currentBike: Bike
        val key = Firebase.database.reference
            .child("users")
            .child(userId)
            .child("bikes").push().key

//        currentBike.bikeId = bikeId
//        currentBike.bikeName = bike.bikeName
//        currentBike.bikeType = bike.bikeType
//        currentBike.bikeEngineType = bike.bikeEngineType
//        currentBike.bikeEngineVolume = bike.bikeEngineVolume
//        currentBike.bikeModelYear = bike.bikeModelYear
//        currentBike.bikeImage = bike.bikeImage
//        currentBike.bikeEdition = bike.bikeEdition

        Firebase.database.reference
            .child("users")
            .child(userId)
            .child("bikes")
            .child(key!!).setValue(bike)
    }

    fun readBikes(userId: String, liveData: MutableLiveData<List<Bike>>) {
        Firebase.database.reference
            .child("users")
            .child(userId)
            .child("bikes")
            .addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val bikes: List<Bike> = snapshot.children.map { dataSnapshot ->
                    dataSnapshot.getValue(Bike::class.java)!!
                }
                liveData.postValue(bikes)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun readBikeNames(userId: String, liveData: MutableLiveData<List<String>>) {
        Firebase.database.reference
            .child("users")
            .child(userId)
            .child("bikes")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val bikeNamesList = mutableListOf<String>()
                    val bikes: List<Bike> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(Bike::class.java)!!
                    }

                    for (bike in bikes.indices) {
                        bikeNamesList.add(bikes[bike].bikeName)
                    }

                    liveData.postValue(bikeNamesList)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }

    fun updateBike(bike: Bike, bikeKey: String, userId: String) {
        val bikeValues = bike.toMap()

        Firebase.database.reference
            .child("users")
            .child(userId)
            .child("bikes")
            .updateChildren(hashMapOf<String, Any?>(
                bikeKey to bikeValues
            ))
    }

    fun deleteBike(bike: Bike) {

    }

}