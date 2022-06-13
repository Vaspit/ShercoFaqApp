package com.example.shercofaqapp.model

data class User (
    var userName: String,
    var userEmail: String,
    var userProfileImage: Long,
    var bikes: List<String>
        )