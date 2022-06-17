package com.example.shercofaqapp.view.workshop

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentWorkshopBinding
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.viewmodel.GarageFragmentViewModel
import java.lang.RuntimeException

class WorkshopFragment : Fragment() {

    lateinit var binding: FragmentWorkshopBinding
    private lateinit var sharedPref: SharedPreferences
    private var bikeId: Long = 0
    private val bikeModel: GarageFragmentViewModel by viewModels()
    lateinit var bike: Bike

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_workshop, container, false)

        binding.apply {

            var currentBikeIndex = 0
            val nestedNavHostFragment = childFragmentManager
                .findFragmentById(R.id.mainContainerView) as? NavHostFragment
            val navController = nestedNavHostFragment?.navController

            //Get bike id
            sharedPref = root.context
                .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            bikeId = sharedPref.getLong("bikeId", 0)

            bikeModel.bikes.observe(viewLifecycleOwner, Observer<List<Bike>> { bike ->
                //find updatable index of bike by bike id
                for (bikeItem: Int in bike.indices) {
                    if (bike[bikeItem].bikeId == bikeId) {
                        currentBikeIndex = bikeItem
                        break
                    }
                }
                currentBikeNameTextView.text = bike[currentBikeIndex].bikeName
            })

            if (navController != null) {
                bottomNavigationView.setupWithNavController(navController)
            } else {
                throw RuntimeException("Controller not found")
            }
        }

        return binding.root

    }

}