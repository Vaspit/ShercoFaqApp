package com.example.shercofaqapp.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

lateinit var AUTH: FirebaseAuth
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var REF_STORAGE_ROOT: StorageReference
lateinit var CURRENT_UID: String

const val USERS_NODE = "users"
const val BIKES_NODE = "bikes"
const val PARTS_NODE = "parts"

const val USER_NAME_FIELD = "userName"

fun initDatabase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    REF_STORAGE_ROOT = FirebaseStorage.getInstance().reference
    CURRENT_UID = AUTH.currentUser!!.uid
}