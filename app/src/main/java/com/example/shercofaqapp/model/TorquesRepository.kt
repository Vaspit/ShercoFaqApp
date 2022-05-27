package com.example.shercofaqapp.model

import android.content.Context
import android.util.Log
import com.example.shercofaqapp.utils.JSONReader
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class TorquesRepository(private val context: Context) {

    fun getTorques(bikeAddress: String): ArrayList<Torque> {
        // Instance of users torques using the data model class
        val torquesArrayList: ArrayList<Torque> = ArrayList()

        try {
            val obj = JSONObject(JSONReader(context).getJSONFromAssets("torques-database.json")!!)
            val torquesArray = obj.getJSONArray(bikeAddress)

            for (i in 0 until torquesArray.length()) {
                val torqueObject = torquesArray.getJSONObject(i)
                val torqueType = torqueObject.getString("torqueType")
                val torqueName = torqueObject.getString("torqueName")
                val torqueValue = torqueObject.getString("torqueValue")
                val threadSize = torqueObject.getString("threadSize")
                val torqueImage = torqueObject.getInt("torqueImage")
                val torqueNote = torqueObject.getString("torqueNote")
                val torque =
                    Torque(torqueType, torqueName, torqueValue, threadSize, torqueImage, torqueNote)
//                // add the torques in the list
                torquesArrayList.add(torque)
            }
        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }

        return torquesArrayList
    }

}