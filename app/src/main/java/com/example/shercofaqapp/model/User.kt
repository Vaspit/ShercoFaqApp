package com.example.shercofaqapp.model

data class User (
    var userName: String,
    var userEmail: String,
    var userProfileImageUrl: String,
    var bikes: List<String>
        ) {

    fun toMap(): Map<String, Any?> {
        return mapOf(

        )
    }
}