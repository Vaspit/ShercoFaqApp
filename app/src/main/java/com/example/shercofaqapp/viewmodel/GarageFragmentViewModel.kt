package com.example.shercofaqapp.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.model.repositories.BikeRepository
import com.example.shercofaqapp.utils.BIKES_NODE
import com.example.shercofaqapp.utils.CURRENT_UID
import com.example.shercofaqapp.utils.REF_STORAGE_ROOT
import com.example.shercofaqapp.utils.USERS_NODE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GarageFragmentViewModel
@Inject
constructor(
    private val bikeRepository: BikeRepository
) : ViewModel() {

    private var _bikeList = MutableLiveData<List<Bike>>()
    val bikeList : LiveData<List<Bike>>
        get() = _bikeList

    private var _bikeImageUrl = MutableLiveData<String>()
    val bikeImageUrl: LiveData<String>
        get() = _bikeImageUrl

    fun createBike(bike: Bike, bikeFirebaseKey: String) {
        bikeRepository.createBike(bike, bikeFirebaseKey)
    }

    fun getBikes() {
        bikeRepository.readBikes(_bikeList)
    }

    fun updateBike(bike: Bike, bikeKey: String) {
        bikeRepository.updateBike(bike, bikeKey)
    }

    fun deleteBike(bike: Bike) {
        bikeRepository.deleteBike(bike.bikeFirebaseKey!!)
    }

    fun getBikeImageUrl(bikeFirebaseKey: String, bikeImageUri: Uri) {
        val path = REF_STORAGE_ROOT
            .child(USERS_NODE)
            .child(CURRENT_UID)
            .child(BIKES_NODE)
            .child(bikeFirebaseKey)

        path.putFile(bikeImageUri).addOnCompleteListener { putFileTask ->
                if (putFileTask.isSuccessful) {
                    path.downloadUrl.addOnCompleteListener { downloadUrlTask ->
                        _bikeImageUrl.value = downloadUrlTask.result.toString()
                    }
                }
            }
    }

}