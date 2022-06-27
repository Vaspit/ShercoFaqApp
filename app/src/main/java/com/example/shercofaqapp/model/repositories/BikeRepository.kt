package com.example.shercofaqapp.model.repositories

import androidx.lifecycle.MutableLiveData
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.utils.BIKES_NODE
import com.example.shercofaqapp.utils.USERS_NODE
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BikeRepository {

    fun createBike(bike: Bike, userId: String, key: String) {

        Firebase.database.reference
            .child(USERS_NODE)
            .child(userId)
            .child(BIKES_NODE)
            .child(key).setValue(bike)
    }

    fun readBikes(liveData: MutableLiveData<List<Bike>>) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid

        Firebase.database.reference
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

    fun updateBike(bike: Bike, bikeKey: String) {
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val bikeValues = bike.toMap()

        Firebase.database.reference
            .child(USERS_NODE)
            .child(userId)
            .child(BIKES_NODE)
            .updateChildren(hashMapOf<String, Any?>(
                bikeKey to bikeValues
            ))
    }

    fun deleteBike(bikeKey: String) {
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        Firebase.database.reference
            .child(USERS_NODE)
            .child(userId)
            .child(BIKES_NODE)
            .child(bikeKey).removeValue()
    }

}