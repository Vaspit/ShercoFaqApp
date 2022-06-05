package com.example.shercofaqapp.viewmodel.parts

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.model.SparePart
import com.example.shercofaqapp.utils.CurrentBikeAddress
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SparePartsViewModel : ViewModel() {

    fun getRecyclerViewAdapter(
        bike: List<Bike>,
        currentBikeIndex: Int,
        currentSparePartType: String,
        currentSparePartName: String
    ): RecyclerViewSparePartsAdapter {

        val currentBikeAddress = CurrentBikeAddress(bike, currentBikeIndex).getCurrentBikeAddress()
        val mRefSpareParts = Firebase.database.getReference("parts")
            .child(currentBikeAddress)
            .child(currentSparePartType)
            .child(currentSparePartName)
        val options = FirebaseRecyclerOptions.Builder<SparePart>()
            .setQuery(mRefSpareParts, SparePart::class.java)
            .build()

        return RecyclerViewSparePartsAdapter(options)
    }

}