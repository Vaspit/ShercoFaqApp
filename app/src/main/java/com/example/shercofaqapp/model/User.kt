package com.example.shercofaqapp.model

data class User (
    var bikes: HashMap<String, HashMap<String, Any>>? = null,
    var userProfileImageUrl: String? = "",
    var userEmail: String? = "",
    var userName: String? = ""
)