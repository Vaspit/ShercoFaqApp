package com.example.shercofaqapp.viewmodel.parts

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.model.SparePart
import com.example.shercofaqapp.utils.PARTS_NODE
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SparePartsViewModel
@Inject
constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    fun getRecyclerViewAdapter(
        currentBikeAddress: String,
        currentSparePartType: String,
        currentSparePartName: String
    ): RecyclerViewSparePartsAdapter {

        val mRefSpareParts = Firebase.database.getReference(PARTS_NODE)
            .child(currentBikeAddress)
            .child(currentSparePartType)
            .child(currentSparePartName)

        val options = FirebaseRecyclerOptions.Builder<SparePart>()
            .setQuery(mRefSpareParts, SparePart::class.java)
            .build()


        return RecyclerViewSparePartsAdapter(options, context)
    }

}

//class SparePartsViewModel(val context: Context) : ViewModel() {
//
//    fun getRecyclerViewAdapter(
//        currentBikeAddress: String,
//        currentSparePartType: String,
//        currentSparePartName: String
//    ): RecyclerViewSparePartsAdapter {
//
//        val mRefSpareParts = Firebase.database.getReference(PARTS_NODE)
//            .child(currentBikeAddress)
//            .child(currentSparePartType)
//            .child(currentSparePartName)
//
//        val options = FirebaseRecyclerOptions.Builder<SparePart>()
//            .setQuery(mRefSpareParts, SparePart::class.java)
//            .build()
//
//
//        return RecyclerViewSparePartsAdapter(options, context)
//    }
//
//}