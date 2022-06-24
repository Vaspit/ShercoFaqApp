package com.example.shercofaqapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.model.User
import com.example.shercofaqapp.model.UserFull
import com.example.shercofaqapp.model.repositories.BikeFireBaseRepository
import com.example.shercofaqapp.model.repositories.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

/** Don't forget to rewrite this class to grabbing just a user instead of user fields*/

class AccountViewModel: ViewModel() {

    private val _user = MutableLiveData<UserFull>()
    val user : LiveData<UserFull>
        get() = _user

    private val _userName = MutableLiveData<String>()
    val userName : LiveData<String>
        get() = _userName

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

    init {
        getUserName()
        getBikeNamesList()
        getUserEmail()
        getUserProfileImage()
    }

    private fun getUserName() {
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        Firebase.database.reference
            .child("users")
            .child(userId)
            .child("userName")
            .get()
            .addOnSuccessListener {
            _userName.value = it.value.toString()
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
    }

    private fun getBikeNamesList() {
        BikeFireBaseRepository().readBikeNames(_bikeNamesList)
    }

    private fun getUserEmail() {
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        Firebase.database.reference
            .child("users")
            .child(userId)
            .child("userEmail")
            .get()
            .addOnSuccessListener {
            _userEmail.value = it.value.toString()
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
    }

    private fun getUserProfileImage() {
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        Firebase.database.reference
            .child("users")
            .child(userId)
            .child("userProfileImage")
            .get()
            .addOnSuccessListener {
            if (it.value != null) {
                _userProfileImageUrl.value = it.value.toString()
            } else {
                _userProfileImageUrl.value = ""
            }
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
    }

    /** Test */
    fun getUser() {
        UserRepository().readUser(_user)
    }

}