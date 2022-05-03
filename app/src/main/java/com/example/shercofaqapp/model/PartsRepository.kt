package com.example.shercofaqapp.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class PartsRepository {

    data class PartsItem (
        val partType: String,
        val partName: String,
        val partDescription: String,
        val partNote: String
    )



    fun fetchParts() {



    }
}