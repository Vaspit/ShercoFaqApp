package com.example.shercofaqapp.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.model.User
import com.example.shercofaqapp.model.repositories.UserRepository
import com.example.shercofaqapp.utils.*
import com.google.firebase.auth.FirebaseUser

class ProfileViewModel: ViewModel() {

    private val _user = MutableLiveData<User>()
    val user : LiveData<User>
        get() = _user

    private var _userProfileImageUrl = MutableLiveData<String>()
    val userProfileImageUrl: LiveData<String>
        get() = _userProfileImageUrl

    fun createUser(
        firebaseUser: FirebaseUser,
        userName: String,
        userEmail: String
    ) {
        UserRepository().createUser(firebaseUser, userName, userEmail)
    }

    fun readUser() {
        UserRepository().readUser(_user)
    }

    /** Update method */

    fun getUserProfileImageUrl(userProfileImageUri: Uri) {
        val path = REF_STORAGE_ROOT
            .child(USERS_NODE)
            .child(CURRENT_UID)
            .child(USER_PROFILE_IMAGES_NODE)
            .child("profileImage")

        path.putFile(userProfileImageUri).addOnCompleteListener { putFileTask ->
            if (putFileTask.isSuccessful) {
                path.downloadUrl.addOnCompleteListener { downloadUrlTask ->
                    _userProfileImageUrl.value = downloadUrlTask.result.toString()
                }
            }
        }
    }

}