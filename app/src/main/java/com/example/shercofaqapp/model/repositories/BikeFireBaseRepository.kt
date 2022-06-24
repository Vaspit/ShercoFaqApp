package com.example.shercofaqapp.model.repositories

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.model.BikeFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BikeFireBaseRepository {

    fun createBike(bike: BikeFirebase, userId: String, key: String) {

        Firebase.database.reference
            .child("users")
            .child(userId)
            .child("bikes")
            .child(key).setValue(bike)
    }

    fun readBikes(liveData: MutableLiveData<List<BikeFirebase>>) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid

        Firebase.database.reference
            .child("users")
            .child(userId!!)
            .child("bikes")
            .addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val bikes = snapshot.children.map { dataSnapshot ->
                    dataSnapshot.getValue(BikeFirebase::class.java)!!
                }
                liveData.postValue(bikes)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun readBikeNames(liveData: MutableLiveData<List<String>>) {
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        Firebase.database.reference
            .child("users")
            .child(userId)
            .child("bikes")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val bikeNamesList = mutableListOf<String>()
                    val bikes: List<BikeFirebase> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(BikeFirebase::class.java)!!
                    }

                    for (bike in bikes.indices) {
                        bikeNamesList.add(bikes[bike].bikeName!!)
                    }

                    liveData.postValue(bikeNamesList)
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }

    fun updateBike(bike: BikeFirebase, bikeKey: String) {
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val bikeValues = bike.toMap()

        Firebase.database.reference
            .child("users")
            .child(userId)
            .child("bikes")
            .updateChildren(hashMapOf<String, Any?>(
                bikeKey to bikeValues
            ))
    }

    fun deleteBike(bikeKey: String) {
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        Firebase.database.reference
            .child("users")
            .child(userId)
            .child("bikes")
            .child(bikeKey).removeValue()
    }

}