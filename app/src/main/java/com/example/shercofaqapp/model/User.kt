package com.example.shercofaqapp.model

data class User (
    var userName: String,
    var userEmail: String,
    var userProfileImageUrl: String,
    var bikes: HashMap<String, Any>
        ) {

    fun toMap(): Map<String, Any?> {
        return mapOf(

        )
    }
}