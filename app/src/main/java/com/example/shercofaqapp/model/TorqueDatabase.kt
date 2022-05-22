package com.example.shercofaqapp.model

import android.content.Context
import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class TorqueDatabase(private val context: Context) {
    lateinit var torque: Torque

    fun getTorque(bikeAddress: String) {
        // Instance of users list using the data model class.
        val torquesList: ArrayList<String> = ArrayList()

        try {
            val obj = JSONObject(getJSONFromAssets()!!)
            val torquesArray = obj.getJSONObject(bikeAddress)


            Log.d("DATABASE_TORQUE",
                torquesArray.toString())
//            for (i in 0 until torquesArray.length()) {
//                // Create a JSONObject for fetching single User's Data
//                val torqueFromFile = torquesArray.getJSONObject(i)
//                // Fetch id store it in variable
//                val torque = torqueFromFile.getString(bikeAddress)
//
//                val currentTorque =
//                    Torque(torqueBikeAddress, torqueType, torqueName, torqueValue, troqueImage)
//
//                // add the details in the list
//                torquesList.add(torque)
//
//                Log.d("DATABASE_TORQUE", torquesList[i])
//            }
        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }
    }

    private fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val torquesJSONFile = context.assets.open("torques-database.json")
            val size = torquesJSONFile.available()
            val buffer = ByteArray(size)
            torquesJSONFile.read(buffer)
            torquesJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return json

    }
}