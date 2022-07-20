package com.example.shercofaqapp.viewmodel.torques

import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.model.Torque
import com.example.shercofaqapp.model.repositories.TorquesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TorquesViewModel
@Inject
constructor(
    private val torquesRepository: TorquesRepository
) : ViewModel() {

    fun getEngineTorques(bikeAddress: String): ArrayList<Torque> {

        val engineTorquesArrayList: ArrayList<Torque> = ArrayList()
        val torquesArrayList = torquesRepository.getTorques(bikeAddress)

        for (i in 0 until torquesArrayList.size) {
            if (torquesArrayList[i].torqueType == "engine") {
                engineTorquesArrayList.add(torquesArrayList[i])
            }
        }

        return engineTorquesArrayList
    }

    fun getChassisTorques(bikeAddress: String): ArrayList<Torque> {
        val chassisTorquesArrayList: ArrayList<Torque> = ArrayList()
        val torquesArrayList = torquesRepository.getTorques(bikeAddress)

        for (i in 0 until torquesArrayList.size) {
            if (torquesArrayList[i].torqueType == "chassis") {
                chassisTorquesArrayList.add(torquesArrayList[i])
            }
        }

        return chassisTorquesArrayList
    }

}