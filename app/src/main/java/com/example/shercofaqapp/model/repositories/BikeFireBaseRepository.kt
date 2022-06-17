package com.example.shercofaqapp.model.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.shercofaqapp.model.Bike
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BikeFireBaseRepository {

    lateinit var bikeList: LiveData<List<Bike>>
    private val databaseReference = Firebase.database.reference

    fun setBikes() {

    }

    fun createBike(bike: Bike, userId: String) {
        val key = databaseReference.child("users").child(userId).child("bikes").push().key
        databaseReference.child("users").child(userId).child("bikes").child(key!!).setValue(bike)
    }

    fun readBikes(): LiveData<List<Bike>> {
        return bikeList
    }


    fun updateBike(bike: Bike, userId: String) {
        val bikeValues = bike.toMap()

        databaseReference
            .child("users")
            .child(userId)
            .updateChildren(hashMapOf<String, Any?>(
                "bikes" to bikeValues
            ))
    }

    fun deleteBike(bike: Bike) {

    }

}