package com.example.shercofaqapp.model.repositories

import androidx.lifecycle.MutableLiveData
import com.example.shercofaqapp.model.User
import com.example.shercofaqapp.utils.*
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserRepository {

    fun createUser(
        firebaseUser: FirebaseUser,
        userName: String,
        userEmail: String
    ) {
        Firebase.database.getReference(USERS_NODE)
            .child(firebaseUser.uid)
            .child(USER_NAME_FIELD).setValue(userName)

        Firebase.database.getReference(USERS_NODE)
            .child(firebaseUser.uid)
            .child(USER_EMAIL_FIELD).setValue(userEmail)

        Firebase.database.getReference(USERS_NODE)
            .child(firebaseUser.uid)
            .child(USER_PROFILE_IMAGE_FIELD).setValue("")

    }

    fun readUser(liveData: MutableLiveData<User>) {

        Firebase.database.reference
            .child(USERS_NODE)
            .child(CURRENT_UID)
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