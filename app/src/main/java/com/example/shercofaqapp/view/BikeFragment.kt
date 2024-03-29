package com.example.shercofaqapp.view

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentAddBikeBinding
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.utils.*
import com.example.shercofaqapp.viewmodel.GarageFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class BikeFragment: Fragment() {

    lateinit var binding: FragmentAddBikeBinding
    private val bikeViewModel: GarageFragmentViewModel by viewModels()
    @Inject lateinit var bike : Bike
    private var isUpdate = false
    private var bikeImageUri = Uri.EMPTY
    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {

            bikeImageUri = result.uriContent
            setBikeImageViewFromCropper()
        } else {
            // an error occurred
            val exception = result.error
        }
    }

    private fun setBikeImageViewFromCropper() {
        val bitmapBikeImage =
            MediaStore.Images.Media.getBitmap(requireContext().contentResolver, bikeImageUri)

        binding.bikeImageView.setImageBitmap(bitmapBikeImage)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        getOuterArguments()

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_bike, container, false)

        createUI(isUpdate)

        if (isUpdate) {
            setBike(binding.root, bike)
            binding.addUpdateBikeButton.setOnClickListener { onUpdate(bike.bikeFirebaseKey!!) }
        } else {
            createFragmentFields()
            binding.addUpdateBikeButton.setOnClickListener { onAdd() }
        }

        binding.putPhotoImageButton.setOnClickListener { onImageClick() }

        return binding.root
    }

    private fun onImageClick() {
        startCrop()
    }

    private fun createUI(isUpdate: Boolean) {
        createFragmentFields()
        if (isUpdate) {
            binding.addUpdateBikeButton.text = getString(R.string.update_bike_button_text)
        }
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
        val bike = Bike()
        val bikeFirebaseKey = REF_DATABASE_ROOT
            .child(USERS_NODE)
            .child(CURRENT_UID)
            .child(BIKES_NODE).push().key

        //Play loading animation
        playLoadingAnimation()

        if (bikeImageUri != Uri.EMPTY) {
            bikeViewModel.getBikeImageUrl(bikeFirebaseKey!!, bikeImageUri)
            bikeViewModel.bikeImageUrl.observe(viewLifecycleOwner, Observer { bikeImageUrl ->
                createBike(bike, bikeFirebaseKey, bikeImageUrl)

                //Go to GarageFragment
                findNavController()
                    .navigate(R.id.action_bikeFragment_to_garageFragment)
            })
        } else {
            createBike(bike, bikeFirebaseKey!!, "")

            //Go to GarageFragment
            findNavController()
                .navigate(R.id.action_bikeFragment_to_garageFragment)
        }

    }

    private fun onUpdate(bikeFirebaseKey: String) {
        //Update bike within Database
        val bike = Bike()

        //Play loading animation
        playLoadingAnimation()

        if (bikeImageUri != Uri.EMPTY) {
            bikeViewModel.getBikeImageUrl(bikeFirebaseKey, bikeImageUri)
            bikeViewModel.bikeImageUrl.observe(viewLifecycleOwner, Observer { bikeImageUrl ->
                updateBike(bike, bikeFirebaseKey, bikeImageUrl)

                //Go to GarageFragment
                findNavController()
                    .navigate(R.id.action_bikeFragment_to_garageFragment)
            })
        } else {
            updateBike(bike, bikeFirebaseKey, "")

            //Go to GarageFragment
            findNavController()
                .navigate(R.id.action_bikeFragment_to_garageFragment)
        }
    }

    private fun setBike(root: View, bike: Bike) {

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

        if (bike.bikeImage != "") {
            Glide.with(requireContext())
                .load(bike.bikeImage)
                .placeholder(R.drawable.ic_baseline_pedal_bike_24)
                .error(R.drawable.ic_baseline_pedal_bike_24)
                .centerInside()
                .into(binding.bikeImageView)
        } else {
            binding.bikeImageView.setImageResource(R.drawable.ic_baseline_pedal_bike_24)
        }
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

        Log.d("BIKE_IMAGE", "${arguments?.getString("bikeImage")}")
    }

    private fun createBike(bike: Bike, bikeFirebaseKey: String, bikeImageUrl: String) {
        bike.bikeName = binding.bikeNameEditText.text.trim().toString()
        bike.bikeModelYear = binding.modelYearSpinner.selectedItem.toString()
        bike.bikeType = binding.typeSpinner.selectedItem.toString()
        bike.bikeEngineType = binding.engineTypeSpinner.selectedItem.toString()
        bike.bikeEngineVolume = binding.engineVolumeSpinner.selectedItem.toString()
        bike.bikeEdition = binding.editionSpinner.selectedItem.toString()
        bike.bikeFirebaseKey = bikeFirebaseKey
        bike.bikeImage =  bikeImageUrl

        bikeViewModel.createBike(bike, bikeFirebaseKey)
    }

    private fun updateBike(bike: Bike, bikeFirebaseKey: String, bikeImageUrl: String) {
        bike.bikeName = binding.bikeNameEditText.text.trim().toString()
        bike.bikeModelYear = binding.modelYearSpinner.selectedItem.toString()
        bike.bikeType = binding.typeSpinner.selectedItem.toString()
        bike.bikeEngineType = binding.engineTypeSpinner.selectedItem.toString()
        bike.bikeEngineVolume = binding.engineVolumeSpinner.selectedItem.toString()
        bike.bikeEdition = binding.editionSpinner.selectedItem.toString()

        if (bikeImageUrl == "") {
            bike.bikeImage = arguments?.getString("bikeImage")
        } else bike.bikeImage = bikeImageUrl

        bike.bikeFirebaseKey = bikeFirebaseKey

        bikeViewModel.updateBike(bike, bikeFirebaseKey)
    }

    private fun playLoadingAnimation() {
        binding.addUpdateBikeButton.visibility = View.GONE
        binding.bikeNameEditText.visibility = View.GONE
        binding.bikeImageView.visibility = View.GONE
        binding.modelYearSpinner.visibility = View.GONE
        binding.editionSpinner.visibility = View.GONE
        binding.typeSpinner.visibility = View.GONE
        binding.engineTypeSpinner.visibility = View.GONE
        binding.engineVolumeSpinner.visibility = View.GONE
        binding.modelYearTextView.visibility = View.GONE
        binding.editionTextView.visibility = View.GONE
        binding.typeTextView.visibility = View.GONE
        binding.engineTypeTextView.visibility = View.GONE
        binding.engineVolumeTextView.visibility = View.GONE

        binding.loadingProgressIndicator.visibility = View.VISIBLE
    }

    private fun startCrop() {
        cropImage.launch(
            options(uri = bikeImageUri) {
                setCropShape(CropImageView.CropShape.OVAL)
                setGuidelines(CropImageView.Guidelines.ON)
                setOutputCompressFormat(Bitmap.CompressFormat.PNG)
            }
        )
    }
}
