package com.example.shercofaqapp.model.repositories

import androidx.lifecycle.MutableLiveData
import com.example.shercofaqapp.model.User
import com.example.shercofaqapp.utils.USERS_NODE
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserRepository {

    fun readUser(liveData: MutableLiveData<User>) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid

        Firebase.database.reference
            .child(USERS_NODE)
            .child(userId!!)
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val userData = snapshot.getValue(User::class.java)

                    liveData.postValue(userData)
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }

}