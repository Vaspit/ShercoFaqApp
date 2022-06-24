package com.example.shercofaqapp.model.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shercofaqapp.model.BikeFirebase
import com.example.shercofaqapp.model.User
import com.example.shercofaqapp.model.UserFull
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class UserRepository {

    fun readUser(liveData: MutableLiveData<UserFull>) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid

        Firebase.database.reference
            .child("users")
            .child(userId!!)
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val userData = snapshot.value
                    Log.d("USER", "userData is: $userData")

                    val transformedUserdata = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(UserFull::class.java)
                    }
                //liveData.postValue(userData[0])
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }



}