package com.example.shercofaqapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.model.UserFull
import com.example.shercofaqapp.model.repositories.UserRepository

class ProfileViewModel: ViewModel() {

    private val _user = MutableLiveData<UserFull>()
    val user : LiveData<UserFull>
        get() = _user

    init {
        getUser()
    }

    private fun getUser() {
        UserRepository().readUser(_user)
    }

}