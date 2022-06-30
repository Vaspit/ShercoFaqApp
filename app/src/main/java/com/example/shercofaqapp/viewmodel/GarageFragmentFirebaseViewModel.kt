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

class GarageFragmentFirebaseViewModel : ViewModel() {

    private var _bikeList = MutableLiveData<List<Bike>>()
    val bikeList : LiveData<List<Bike>>
        get() = _bikeList

    private var _bikeImageUrl = MutableLiveData<String>()
    val bikeImageUrl: LiveData<String>
        get() = _bikeImageUrl

    fun createBike(bike: Bike, bikeFirebaseKey: String) {
        BikeRepository().createBike(bike, bikeFirebaseKey)
    }

    fun getBikes() {
        BikeRepository().readBikes(_bikeList)
    }

    fun updateBike(bike: Bike, bikeKey: String) {
        BikeRepository().updateBike(bike, bikeKey)
    }

    fun deleteBike(bike: Bike) {
        BikeRepository().deleteBike(bike.bikeFirebaseKey!!)
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