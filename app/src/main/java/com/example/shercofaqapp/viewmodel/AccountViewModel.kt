package com.example.shercofaqapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AccountViewModel: ViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName : LiveData<String>
        get() = _userName

    private val _bikesList = MutableLiveData<List<String>>()
    val bikeList : LiveData<List<String>>
        get() = _bikesList

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
        getBikeList(userId, database)
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

    private fun getBikeList(userId: String, database: DatabaseReference) {
        database.child("users").child(userId).child("bikes").get().addOnSuccessListener {
            if ((it.value as List<String>).isNotEmpty()) {
                _bikesList.value = it.value as List<String>?
            } else {
                _bikesList.value = listOf("There are no bikes")
            }
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
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