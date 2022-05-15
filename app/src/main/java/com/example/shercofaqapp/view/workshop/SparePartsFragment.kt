package com.example.shercofaqapp.view.workshop

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentSparePartsBinding
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.model.SparePart
import com.example.shercofaqapp.viewmodel.GarageFragmentViewModel
import com.example.shercofaqapp.viewmodel.RecyclerViewSparePartsAdapter
import com.example.shercofaqapp.viewmodel.SparePartsFragmentViewModel
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SparePartsFragment : Fragment() {

    lateinit var binding: FragmentSparePartsBinding
    private var bikeId: Long = 0
    private val bikeModel: GarageFragmentViewModel by viewModels()
    private lateinit var sharedPref: SharedPreferences
//    private val sparePartsModel: SparePartsFragmentViewModel by viewModels()
    lateinit var bike: Bike
    private var currentSparePartAddress = ""
    private lateinit var currentSparePartType: String
    private lateinit var currentSparePartName: String
    private var sparePartsArrayList = emptyList<SparePart>()
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: FirebaseRecyclerAdapter<SparePart,SparePartsHolder>
    private lateinit var mRefSpareParts:DatabaseReference
    private var currentBikeIndex = 0

    class SparePartsHolder(view: View): RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.sparePartsTextView)
        val image: ImageView = view.findViewById(R.id.sparePartsImageView)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_spare_parts, container, false)

        binding.apply {
            val bikeObserver = Observer<List<Bike>> { bike ->
                //find updatable index of bike by bike id
                for (bikeItem: Int in bike.indices) {
                    if (bike[bikeItem].bikeId == bikeId) {
                        currentBikeIndex = bikeItem
                        break
                    }
                }

                initRecyclerView(bike, currentBikeIndex)



            }

            //get bike id and current spare part
            sharedPref = root.context
                .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            bikeId = sharedPref.getLong("bikeId", 0)
            currentSparePartType = sharedPref.getString("currentSparePartType", "").toString()
            currentSparePartName = sharedPref.getString("currentSparePartName", "").toString()

            //forming parts address
            bikeModel.bikes.observe(viewLifecycleOwner, bikeObserver)
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initRecyclerView(bike: List<Bike>, currentBikeIndex: Int) {
        mRecyclerView = binding.sparePartsRecyclerView
        mRefSpareParts = Firebase.database.getReference("parts")
            .child(getCurrentSparePartAddress(bike, currentBikeIndex))
            .child(currentSparePartType)
            .child(currentSparePartName)

        val options = FirebaseRecyclerOptions.Builder<SparePart>()
            .setQuery(mRefSpareParts, SparePart::class.java)
            .build()
        mAdapter = object: FirebaseRecyclerAdapter<SparePart, SparePartsHolder>(options) {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SparePartsHolder {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.spare_parts_item, parent, false)

                return SparePartsHolder(view)
            }

            override fun onBindViewHolder(
                holder: SparePartsHolder,
                position: Int,
                model: SparePart
            ) {
                holder.name.text = model.sparePartName
                holder.image.setImageResource(R.drawable.ic_baseline_parts)
//                holder.itemView.setOnClickListener {
//                    editor.putString("currentSparePartName", model.sparePartName.toString().trim())
//                    editor.putString("currentSparePartDescription", model.sparePartDescription.toString().trim())
//                    editor.putString("currentSparePartLink", model.sparePartLink.toString().trim())
//                    editor.putInt("currentSparePartImage", model.sparePartImage!!)
//                    editor.apply()
//                    Navigation.findNavController(requireView())
//                        .navigate(R.id.action_sparePartsFragment_to_sparePartFragment)
//                }
            }

        }

        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mRecyclerView.adapter = mAdapter
        mAdapter.startListening()

    }

    private fun getCurrentSparePartAddress(
        bike: List<Bike>,
        currentBikeIndex: Int
    ): String {
        currentSparePartAddress =
            bike[currentBikeIndex].bikeModelYear +
                    bike[currentBikeIndex].bikeType +
                    bike[currentBikeIndex].bikeEngineType +
                    bike[currentBikeIndex].bikeEngineVolume +
                    bike[currentBikeIndex].bikeEdition

       return currentSparePartAddress.trim()
    }
}