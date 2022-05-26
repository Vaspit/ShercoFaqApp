package com.example.shercofaqapp.model

import android.content.Context
import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class TorquesRepository(private val context: Context) {

    fun getTorques(bikeAddress: String): ArrayList<Torque> {
        // Instance of users list using the data model class.
        val torquesArrayList: ArrayList<Torque> = ArrayList()

        try {
            val obj = JSONObject(getJSONFromAssets()!!)
            val torquesArray = obj.getJSONArray(bikeAddress)

            for (i in 0 until torquesArray.length()) {
                val torqueObject = torquesArray.getJSONObject(i)
                Log.d("DATABASE_TORQUE", torqueObject.toString())
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