package com.example.shercofaqapp.viewmodel.parts

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.model.BikeFirebase
import com.example.shercofaqapp.model.SparePart
import com.example.shercofaqapp.utils.CurrentBikeAddress
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SparePartsViewModel(val context: Context) : ViewModel() {

    fun getRecyclerViewAdapter(
        currentBikeAddress: String,
        currentSparePartType: String,
        currentSparePartName: String
    ): RecyclerViewSparePartsAdapter {

        val mRefSpareParts = Firebase.database.getReference("parts")
            .child(currentBikeAddress)
            .child(currentSparePartType)
            .child(currentSparePartName)

        val options = FirebaseRecyclerOptions.Builder<SparePart>()
            .setQuery(mRefSpareParts, SparePart::class.java)
            .build()


        return RecyclerViewSparePartsAdapter(options, context)
    }

}