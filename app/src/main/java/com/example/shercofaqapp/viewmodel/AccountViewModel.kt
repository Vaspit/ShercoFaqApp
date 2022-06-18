package com.example.shercofaqapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.model.repositories.BikeFireBaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

/** Don't forget to rewrite this class to grabbing just a user instead of user fields*/

class AccountViewModel: ViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName : LiveData<String>
        get() = _userName

    private val _bikesList = MutableLiveData<List<Bike>>()
    val bikesList : LiveData<List<Bike>>
        get() = _bikesList

    /** Get bike names */
    private val _bikeNamesList = MutableLiveData<List<String>>()
    val bikeNamesList : LiveData<List<String>>
        get() = _bikeNamesList

    private val _userEmail = MutableLiveData<String>()
    val userEmail : LiveData<String>
        get() = _userEmail

    private val _userProfileImageUrl = MutableLiveData<String>()
    val userProfileImage : LiveData<String>
        get() = _userProfileImageUrl

    fun setUser() {
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val database = Firebase.database.reference

        getUserName(userId, database)
        getBikesList(userId, database)
        getBikeNamesList(userId, database)
        getUserEmail(userId, database)
        getUserProfileImage(userId, database)
    }

    private fun getUserName(userId: String, database: DatabaseReference) {
        database.child("users").child(userId).child("userName").get().addOnSuccessListener {
            _userName.value = it.value.toString()
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
    }

    private fun getBikesList(userId: String, database: DatabaseReference) {
        BikeFireBaseRepository().readBikes(userId, database, _bikesList)
        Log.d("BIKES", _bikesList.value.toString())
    }

    private fun getBikeNamesList(userId: String, database: DatabaseReference) {
        BikeFireBaseRepository().readBikeNames(userId, database, _bikeNamesList)
    }

    private fun getUserEmail(userId: String, database: DatabaseReference) {
        database.child("users").child(userId).child("userEmail").get().addOnSuccessListener {
            _userEmail.value = it.value.toString()
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
    }

    private fun getUserProfileImage(userId: String, database: DatabaseReference) {
        database.child("users").child(userId).child("userProfileImage").get().addOnSuccessListener {
            if (it.value != null) {
                _userProfileImageUrl.value = it.value.toString()
            } else {
                _userProfileImageUrl.value = ""
            }
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
    }

}