package com.example.shercofaqapp.model

import org.json.JSONObject

data class UserFull (
    var bikes: HashMap<String, HashMap<String, String>>? = null,
    var userProfileImageUrl: String? = "",
    var userEmail: String? = "",
    var userName: String? = ""
) {

}