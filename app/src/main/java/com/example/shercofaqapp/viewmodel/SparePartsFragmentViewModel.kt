package com.example.shercofaqapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.model.SparePart
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SparePartsFragmentViewModel: ViewModel() {

    fun getSpareParts(
        currentSparePartAddress: String,
        currentSparePartType: String,
        currentSparePartName: String,
    ): ArrayList<SparePart> {

        val database = Firebase.database.reference
        val sparePartsArrayList = arrayListOf<SparePart>()
        var sparePart = SparePart()

        //need to fill an array
        database.child("parts").child(currentSparePartAddress)
            .child(currentSparePartType)
            .child(currentSparePartName)
            .get().addOnSuccessListener {
            Log.d("SPARE_PARTS", it.value.toString())
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

        return sparePartsArrayList
    }

}