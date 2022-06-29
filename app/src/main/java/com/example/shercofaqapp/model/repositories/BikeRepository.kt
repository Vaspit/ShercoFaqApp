package com.example.shercofaqapp.model.repositories

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.utils.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.net.URL

class BikeRepository {

    fun createBike(bike: Bike, bikeFirebaseKey: String) {

        REF_DATABASE_ROOT
            .child(USERS_NODE)
            .child(CURRENT_UID)
            .child(BIKES_NODE)
            .child(bikeFirebaseKey).setValue(bike)
    }

    fun readBikes(liveData: MutableLiveData<List<Bike>>) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid

        REF_DATABASE_ROOT
            .child(USERS_NODE)
            .child(userId!!)
            .child(BIKES_NODE)
            .addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val bikes = snapshot.children.map { dataSnapshot ->
                    dataSnapshot.getValue(Bike::class.java)!!
                }
                liveData.postValue(bikes)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun updateBike(bike: Bike, bikeFirebaseKey: String) {
        val bikeValues = bike.toMap()

        REF_DATABASE_ROOT
            .child(USERS_NODE)
            .child(CURRENT_UID)
            .child(BIKES_NODE)
            .updateChildren(hashMapOf<String, Any?>(
                bikeFirebaseKey to bikeValues
            ))
    }

    fun deleteBike(bikeKey: String) {
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        REF_DATABASE_ROOT
            .child(USERS_NODE)
            .child(userId)
            .child(BIKES_NODE)
            .child(bikeKey).removeValue()
    }

}