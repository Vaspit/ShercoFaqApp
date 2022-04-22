package com.example.shercofaqapp.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentAddBikeBinding
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.viewmodel.GarageFragmentViewModel

class BikeFragment : Fragment() {

    lateinit var binding: FragmentAddBikeBinding
    private val model: GarageFragmentViewModel by viewModels()
    lateinit var bike: Bike
    private var isUpdate = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val sharedPref = requireActivity()
            .getSharedPreferences("MyPreferences",Context.MODE_PRIVATE)

        isUpdate = sharedPref.getBoolean("isUpdate", false)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_bike, container, false)

        binding.apply {

            createUI(isUpdate)

            if (isUpdate) {

                val modelYearArrayList = root.resources.getStringArray(R.array.model_year_spinner_text)
                val bikeTypeArrayList = root.resources.getStringArray(R.array.bike_type_spinner_text)
                val engineTypeArrayList = root.resources.getStringArray(R.array.engine_type_spinner_text)
                val engineVolumeArrayList = root.resources.getStringArray(R.array.engine_volume_spinner_text)
                val editionArrayList = root.resources.getStringArray(R.array.edition_spinner_text)
                val bikeId = sharedPref.getLong("bikeId", 0)
                var updatingBikeIndex = 0

                //Set an observer for selected bike from recyclerview
                val bikeObserver = Observer<List<Bike>> { bike ->

                    //find updatable index of bike by bike id
                    for (bikeItem: Int in bike.indices) {

                        if (bike[bikeItem].bikeId == bikeId) {
                            updatingBikeIndex = bikeItem
                            break
                        }
                    }

                    bikeNameEditText.setText(bike[updatingBikeIndex].bikeName)
                    modelYearSpinner.setSelection(modelYearArrayList
                        .indexOf(bike[updatingBikeIndex].bikeModelYear))
                    typeSpinner.setSelection(bikeTypeArrayList
                        .indexOf(bike[updatingBikeIndex].bikeType))
                    engineTypeSpinner.setSelection(engineTypeArrayList
                        .indexOf(bike[updatingBikeIndex].bikeEngineType))
                    engineVolumeSpinner.setSelection(engineVolumeArrayList
                        .indexOf(bike[updatingBikeIndex].bikeEngineVolume))
                    editionSpinner.setSelection(editionArrayList
                        .indexOf(bike[updatingBikeIndex].bikeEdition))
                    bikeImageView.setImageResource(bike[updatingBikeIndex].bikeImage)

                }

                //Get the bike data from selected bike item
                model.bikes.observe(viewLifecycleOwner, bikeObserver)

                addBikeButton.setOnClickListener {

                    //Update bike within Database
                    val bike = Bike()
//                    bike.bikeId = (bikeAdapterPosition + 1).toLong()
                    bike.bikeId = bikeId
                    bike.bikeName = bikeNameEditText.text.trim().toString()
                    bike.bikeModelYear = modelYearSpinner.selectedItem.toString()
                    bike.bikeType = typeSpinner.selectedItem.toString()
                    bike.bikeEngineType = engineTypeSpinner.selectedItem.toString()
                    bike.bikeEngineVolume = engineVolumeSpinner.selectedItem.toString()
                    bike.bikeEdition = editionSpinner.selectedItem.toString()
                    bike.bikeImage = R.drawable.garage_item_icon

                    model.updateBike(bike)

                    //Go to GarageFragment
                    Navigation.findNavController(requireView())
                        .navigate(R.id.action_bikeFragment_to_garageFragment)

                }

            } else {

                createFragmentFields()

                addBikeButton.setOnClickListener {

                    //Add new bike to Database
                    val bike = Bike()
                    bike.bikeName = bikeNameEditText.text.trim().toString()
                    bike.bikeModelYear = modelYearSpinner.selectedItem.toString()
                    bike.bikeType = typeSpinner.selectedItem.toString()
                    bike.bikeEngineType = engineTypeSpinner.selectedItem.toString()
                    bike.bikeEngineVolume = engineVolumeSpinner.selectedItem.toString()
                    bike.bikeEdition = editionSpinner.selectedItem.toString()
                    bike.bikeImage = R.drawable.garage_item_icon

                    model.addNewBike(bike)

                    //Go to GarageFragment
                    Navigation.findNavController(requireView())
                        .navigate(R.id.action_bikeFragment_to_garageFragment)

                }

            }

        }

        return binding.root

    }

    private fun createUI(isUpdate: Boolean) {

        createFragmentFields()

        if (isUpdate) {

            binding.addBikeButton.text = getString(R.string.update_bike_button_text)

        } else {

            binding.addBikeButton.text = getString(R.string.add_bike_button_text)

        }

    }

    private fun createFragmentFields() {

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.bike_type_spinner_text,
            R.layout.spinner_item_3
        ).also { typeSpinnerAdapter ->
            // Specify the layout to use when the list of choices appears
            typeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.typeSpinner.adapter = typeSpinnerAdapter
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.model_year_spinner_text,
            R.layout.spinner_item_3
        ).also { modelYearSpinnerAdapter ->
            // Specify the layout to use when the list of choices appears
            modelYearSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.modelYearSpinner.adapter = modelYearSpinnerAdapter
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.engine_type_spinner_text,
            R.layout.spinner_item_3
        ).also { engineTypeSpinnerAdapter ->
            // Specify the layout to use when the list of choices appears
            engineTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.engineTypeSpinner.adapter = engineTypeSpinnerAdapter
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.engine_volume_spinner_text,
            R.layout.spinner_item_3
        ).also { engineVolumeSpinnerAdapter ->
            // Specify the layout to use when the list of choices appears
            engineVolumeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.engineVolumeSpinner.adapter = engineVolumeSpinnerAdapter
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.edition_spinner_text,
            R.layout.spinner_item_3
        ).also { editionSpinnerAdapter ->
            // Specify the layout to use when the list of choices appears
            editionSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.editionSpinner.adapter = editionSpinnerAdapter
        }

    }

}