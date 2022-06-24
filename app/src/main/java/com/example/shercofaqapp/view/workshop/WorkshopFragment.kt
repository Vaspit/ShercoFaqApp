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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentWorkshopBinding
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.model.BikeFirebase
import com.example.shercofaqapp.utils.CurrentBikeAddress
import com.example.shercofaqapp.viewmodel.GarageFragmentViewModel
import com.example.shercofaqapp.viewmodel.WorkshopFragmentViewModel
import java.lang.RuntimeException

class WorkshopFragment : Fragment() {

    lateinit var binding: FragmentWorkshopBinding
    private val model: WorkshopFragmentViewModel by viewModels()
    private lateinit var bike: BikeFirebase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_workshop, container, false)

//        model = ViewModelProvider(this)[WorkshopFragmentViewModel::class.java]

        getOuterArguments(binding.root)

        model.setBike(bike)
        model.bike.observe(viewLifecycleOwner, Observer { bike ->
            binding.currentBikeNameTextView.text = bike.bikeName
        })

        val nestedNavHostFragment = childFragmentManager
            .findFragmentById(R.id.mainContainerView) as? NavHostFragment
        val navController = nestedNavHostFragment?.navController

        if (navController != null) {
            binding.bottomNavigationView.setupWithNavController(navController)
        } else {
            throw RuntimeException("Controller not found")
        }

        return binding.root
    }

    private fun getOuterArguments(view: View) {
        bike = BikeFirebase(
            arguments?.getString("bikeName"),
            arguments?.getString("bikeModelYear"),
            arguments?.getString("bikeType"),
            arguments?.getString("bikeEngineType"),
            arguments?.getString("bikeEngineVolume"),
            arguments?.getString("bikeEdition"),
            arguments?.getInt("bikeImage"),
            arguments?.getString("bikeFirebaseKey")
        )

        val currentBikeAddress = CurrentBikeAddress().getCurrentBikeAddress(bike)
        val sharedPref = view.context
            .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        sharedPref.edit().putString("currentBikeAddress", currentBikeAddress).apply()
    }

}