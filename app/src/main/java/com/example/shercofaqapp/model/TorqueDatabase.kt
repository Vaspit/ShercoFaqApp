package com.example.shercofaqapp.model

import android.content.Context
import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class TorqueDatabase(private val context: Context) {
    lateinit var torque: Torque

    fun getTorque(bikeAddress: String): ArrayList<Torque> {
        // Instance of users list using the data model class.
        val torquesArrayList: ArrayList<Torque> = ArrayList()
        var index = 0

        try {
            val obj = JSONObject(getJSONFromAssets()!!)
            val json = obj.getJSONObject(bikeAddress)
            val torqueName: String
            val torqueType: String
            val torqueValue: String
            val torqueImage: Int

            Log.d("DATABASE_TORQUE",
                json.toString())
            Log.d("DATABASE_TORQUE", json.length().toString())

//            for (item in json.keys()) {
//
//            }
        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }

        return torquesArrayList
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