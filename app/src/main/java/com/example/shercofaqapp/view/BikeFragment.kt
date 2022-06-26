package com.example.shercofaqapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentAddBikeBinding
import com.example.shercofaqapp.model.BikeFirebase
import com.example.shercofaqapp.viewmodel.GarageFragmentFirebaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BikeFragment : Fragment() {

    lateinit var binding: FragmentAddBikeBinding
    private lateinit var model: GarageFragmentFirebaseViewModel
    private var bike = BikeFirebase()
    private var isUpdate = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        getOuterArguments()

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_bike, container, false)
        model = ViewModelProvider(this)[GarageFragmentFirebaseViewModel::class.java]

        binding.apply {
            createUI(isUpdate)

            if (isUpdate) {
                setBike(binding.root, bike)
                addUpdateBikeButton.setOnClickListener { onUpdate(bike.bikeFirebaseKey!!) }
            } else {
                createFragmentFields()
                addUpdateBikeButton.setOnClickListener { onAdd() }
            }
            bikeImageView.setOnClickListener { onImageClick() }
        }
        return binding.root
    }

    private fun onImageClick() {

    }

    private fun createUI(isUpdate: Boolean) {
        createFragmentFields()
        if (isUpdate) { binding.addUpdateBikeButton.text = getString(R.string.update_bike_button_text) }
        else { binding.addUpdateBikeButton.text = getString(R.string.add_bike_button_text) }
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

    private fun onAdd() {
        //Add new bike to Database
        val bike = BikeFirebase()
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        val key = Firebase.database.reference
            .child("users")
            .child(userId)
            .child("bikes").push().key

        bike.bikeName = binding.bikeNameEditText.text.trim().toString()
        bike.bikeModelYear = binding.modelYearSpinner.selectedItem.toString()
        bike.bikeType = binding.typeSpinner.selectedItem.toString()
        bike.bikeEngineType = binding.engineTypeSpinner.selectedItem.toString()
        bike.bikeEngineVolume = binding.engineVolumeSpinner.selectedItem.toString()
        bike.bikeEdition = binding.editionSpinner.selectedItem.toString()
        bike.bikeFirebaseKey = key
        bike.bikeImage = ""

        model.createBike(bike, userId, key!!)

        //Go to GarageFragment
        findNavController()
            .navigate(R.id.action_bikeFragment_to_garageFragment)
    }

    private fun onUpdate(bikeFirebaseKey: String) {
        //Update bike within Database
        val bike = BikeFirebase()

        bike.bikeName = binding.bikeNameEditText.text.trim().toString()
        bike.bikeModelYear = binding.modelYearSpinner.selectedItem.toString()
        bike.bikeType = binding.typeSpinner.selectedItem.toString()
        bike.bikeEngineType = binding.engineTypeSpinner.selectedItem.toString()
        bike.bikeEngineVolume = binding.engineVolumeSpinner.selectedItem.toString()
        bike.bikeEdition = binding.editionSpinner.selectedItem.toString()
        bike.bikeImage = ""
        bike.bikeFirebaseKey = bikeFirebaseKey

        model.updateBike(bike, bikeFirebaseKey)

        //Go to GarageFragment
        findNavController()
            .navigate(R.id.action_bikeFragment_to_garageFragment)
    }

    private fun setBike(root: View, bike: BikeFirebase) {

        val modelYearArrayList = root.resources.getStringArray(R.array.model_year_spinner_text)
        val bikeTypeArrayList = root.resources.getStringArray(R.array.bike_type_spinner_text)
        val engineTypeArrayList = root.resources.getStringArray(R.array.engine_type_spinner_text)
        val engineVolumeArrayList = root.resources.getStringArray(R.array.engine_volume_spinner_text)
        val editionArrayList = root.resources.getStringArray(R.array.edition_spinner_text)

        binding.bikeNameEditText.setText(bike.bikeName)
        binding.modelYearSpinner.setSelection(modelYearArrayList.indexOf(bike.bikeModelYear))
        binding.typeSpinner.setSelection(bikeTypeArrayList.indexOf(bike.bikeType))
        binding.engineTypeSpinner.setSelection(engineTypeArrayList.indexOf(bike.bikeEngineType))
        binding.engineVolumeSpinner.setSelection(engineVolumeArrayList.indexOf(bike.bikeEngineVolume))
        binding.editionSpinner.setSelection(editionArrayList.indexOf(bike.bikeEdition))
        Glide.with(requireContext())
            .load("https://static.tildacdn.com/tild3266-3637-4664-b139-306231623934/1.png")
            .placeholder(R.drawable.ic_baseline_parts)
            .error(R.drawable.ic_baseline_parts)
            .centerInside()
            .into(binding.bikeImageView)
    }

    private fun getOuterArguments() {
        isUpdate = arguments?.getBoolean("isUpdate")!!

        bike.bikeName = arguments?.getString("bikeName")
        bike.bikeModelYear = arguments?.getString("bikeModelYear")
        bike.bikeType = arguments?.getString("bikeType")
        bike.bikeEngineType = arguments?.getString("bikeEngineType")
        bike.bikeEngineVolume = arguments?.getString("bikeEngineVolume")
        bike.bikeEdition = arguments?.getString("bikeEdition")
        bike.bikeImage = arguments?.getString("bikeImage")
        bike.bikeFirebaseKey = arguments?.getString("bikeFirebaseKey")
    }

}