package com.example.shercofaqapp.viewmodel

import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentSparePartsBinding
import com.example.shercofaqapp.databinding.SparePartsItemBinding
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.model.SparePart
import com.example.shercofaqapp.view.workshop.SparePartsFragment
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SparePartsAdapter(
    private val currentSparePartAddress: String,
    private val currentSparePartType: String,
    private val currentSparePartName: String
    ) {

    lateinit var binding: FragmentSparePartsBinding
    private lateinit var sharedPref: SharedPreferences
    private lateinit var mAdapter: FirebaseRecyclerAdapter<SparePart, SparePartsFragment.SparePartsHolder>
    private lateinit var mRefSpareParts: DatabaseReference

    private fun initRecyclerView() {
//        val mRecyclerView = binding.sparePartsRecyclerView
        val editor = sharedPref.edit()
        mRefSpareParts = Firebase.database.getReference("parts")
            .child(currentSparePartAddress)
            .child(currentSparePartType)
            .child(currentSparePartName)

        Log.d("RecyclerViewDebugging", "" + mRefSpareParts)

        val options = FirebaseRecyclerOptions.Builder<SparePart>()
            .setQuery(mRefSpareParts, SparePart::class.java)
            .build()
        mAdapter = object: FirebaseRecyclerAdapter<SparePart, SparePartsFragment.SparePartsHolder>(options) {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SparePartsFragment.SparePartsHolder {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.spare_parts_item, parent, false)

                return SparePartsFragment.SparePartsHolder(view)
            }

            override fun onBindViewHolder(
                holder: SparePartsFragment.SparePartsHolder,
                position: Int,
                model: SparePart
            ) {
                holder.name.text = model.sparePartName
                holder.image.setImageResource(R.drawable.ic_baseline_parts)
                holder.itemView.setOnClickListener {
                    editor.putString("currentSparePartName", model.sparePartName.toString().trim())
                    editor.putString("currentSparePartDescription", model.sparePartDescription.toString().trim())
                    editor.putString("currentSparePartLink", model.sparePartLink.toString().trim())
                    editor.putInt("currentSparePartImage", model.sparePartImage!!)
                    editor.apply()
                    Navigation.findNavController(holder.itemView)
                        .navigate(R.id.action_sparePartsFragment_to_sparePartFragment)
                }
            }


        }
    }

}