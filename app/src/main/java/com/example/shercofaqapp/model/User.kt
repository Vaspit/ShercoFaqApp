package com.example.shercofaqapp.model

import com.example.shercofaqapp.R

data class User(val email: String?, val name: String?, val profileImage: Int ?= R.drawable.default_profile_icon)
