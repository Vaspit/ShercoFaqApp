package com.example.shercofaqapp.viewmodel.torques

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.model.Torque
import com.example.shercofaqapp.model.repositories.TorquesRepository

class TorquesViewModel: ViewModel() {

    fun getEngineTorques(context: Context, bikeAddress: String): ArrayList<Torque> {

        val engineTorquesArrayList: ArrayList<Torque> = ArrayList()
        val torquesArrayList = TorquesRepository(context).getTorques(bikeAddress)

        for (i in 0 until torquesArrayList.size) {
            if (torquesArrayList[i].torqueType == "engine") {
                engineTorquesArrayList.add(torquesArrayList[i])
            }
        }

        return engineTorquesArrayList
    }

    fun getChassisTorques(context: Context, bikeAddress: String): ArrayList<Torque> {
        val chassisTorquesArrayList: ArrayList<Torque> = ArrayList()
        val torquesArrayList = TorquesRepository(context).getTorques(bikeAddress)

        for (i in 0 until torquesArrayList.size) {
            if (torquesArrayList[i].torqueType == "chassis") {
                chassisTorquesArrayList.add(torquesArrayList[i])
            }
        }

        return chassisTorquesArrayList
    }

}