package com.example.shercofaqapp.viewmodel.torques

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.model.Torque
import com.example.shercofaqapp.model.TorqueRepository

class TorquesViewModel(@SuppressLint("StaticFieldLeak") private val context: Context): ViewModel() {

    fun getEngineTorques(bikeAddress: String): ArrayList<Torque> {

        val engineTorquesArrayList: ArrayList<Torque> = ArrayList()
        val torquesArrayList = TorqueRepository(context).getTorques(bikeAddress)

        for (i in 0 until torquesArrayList.size) {
            if (torquesArrayList[i].torqueType == "engine") {
                engineTorquesArrayList.add(torquesArrayList[i])
            }
        }

        return engineTorquesArrayList
    }

    fun getChassisTorques(bikeAddress: String): ArrayList<Torque> {
        val chassisTorquesArrayList: ArrayList<Torque> = ArrayList()
        val torquesArrayList = TorqueRepository(context).getTorques(bikeAddress)

        for (i in 0 until torquesArrayList.size) {
            if (torquesArrayList[i].torqueType == "chassis") {
                chassisTorquesArrayList.add(torquesArrayList[i])
            }
        }

        return chassisTorquesArrayList
    }

}