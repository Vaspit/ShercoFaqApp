package com.example.shercofaqapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.model.User
import com.example.shercofaqapp.model.repositories.UserRepository

class ProfileViewModel: ViewModel() {

    private val _user = MutableLiveData<User>()
    val user : LiveData<User>
        get() = _user

    init {
        getUser()
    }

    private fun getUser() {
        UserRepository().readUser(_user)
    }

}