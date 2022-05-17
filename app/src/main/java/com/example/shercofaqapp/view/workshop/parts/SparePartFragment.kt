package com.example.shercofaqapp.view.workshop.parts

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentSparePartBinding
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.viewmodel.GarageFragmentViewModel

class SparePartFragment : Fragment() {

    private lateinit var sharedPref: SharedPreferences
    lateinit var binding: FragmentSparePartBinding
    private val bikeModel: GarageFragmentViewModel by viewModels()
    private var bikeId: Long = 0
    lateinit var bike: Bike
    private var currentBikeIndex = 0
    private lateinit var currentSparePartDescription: String
    private lateinit var currentSparePartName: String
    private lateinit var currentSparePartLink: String
    private var currentSparePartImage = R.drawable.ic_baseline_parts

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_spare_part, container, false)

        binding.apply {
            val bikeObserver = Observer<List<Bike>> { bike ->
                //find updatable index of bike by bike id
                for (bikeItem: Int in bike.indices) {
                    if (bike[bikeItem].bikeId == bikeId) {
                        currentBikeIndex = bikeItem
                        break
                    }
                }
                sparePartImageView.setImageResource(currentSparePartImage)
                sparePartDescriptionTextView.text = currentSparePartDescription
            }

            //get bike id and current spare part
            sharedPref = root.context
                .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            bikeId = sharedPref.getLong("bikeId", 0)
            currentSparePartName =
                sharedPref.getString("currentSparePartName", "").toString()
            currentSparePartDescription =
                sharedPref.getString("currentSparePartDescription", "").toString()
            currentSparePartLink =
                sharedPref.getString("currentSparePartLink", "").toString()
            currentSparePartImage =
                sharedPref.getInt("currentSparePartImage", R.drawable.ic_baseline_parts)

            bikeModel.bikes.observe(viewLifecycleOwner, bikeObserver)

            buyButton.setOnClickListener {
                Log.d("LINK1", currentSparePartLink)
                if (currentSparePartLink != "") {
                    gotToUrl(currentSparePartLink)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "There are no parts in the shop yet...",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        return binding.root
    }

    private fun gotToUrl(currentSparePartLink: String) {
        val uri = Uri.parse(currentSparePartLink)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}