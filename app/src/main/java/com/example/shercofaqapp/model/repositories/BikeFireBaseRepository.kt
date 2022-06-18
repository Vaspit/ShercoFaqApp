package com.example.shercofaqapp.model.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.shercofaqapp.model.Bike
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class BikeFireBaseRepository {

    private var bikesList: List<Bike> = listOf()
    private var bikeNamesList = mutableListOf<String>()
    private val databaseReference = Firebase.database.reference

    fun setBikes() {

    }

    fun createBike(bike: Bike, userId: String) {
        val key = databaseReference.child("users").child(userId).child("bikes").push().key
        databaseReference.child("users").child(userId).child("bikes").child(key!!).setValue(bike)
    }

    fun readBikes(userId: String, database: DatabaseReference, liveData: MutableLiveData<List<Bike>>) {

        database
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

    fun readBikeNames(userId: String, database: DatabaseReference, liveData: MutableLiveData<List<String>>) {

        database
            .child("users")
            .child(userId)
            .child("bikes")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
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