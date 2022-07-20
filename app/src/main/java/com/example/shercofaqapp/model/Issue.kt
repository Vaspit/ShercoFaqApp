package com.example.shercofaqapp.model

data class Issue (
    val issueDescription: String? = "Description",
    val issueSolutions: ArrayList<Solution>? = arrayListOf()
    )