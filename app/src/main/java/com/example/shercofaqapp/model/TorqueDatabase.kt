package com.example.shercofaqapp.model

import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class TorqueDatabase(private val context: Context) {
    lateinit var torque: Torque
    lateinit var torquesList: ArrayList<Torque>

    fun getTorque() {
        // Instance of users list using the data model class.
        val usersList: ArrayList<Torque> = ArrayList()

//        try {
//            // As we have JSON object, so we are getting the object
//            //Here we are calling a Method which is returning the JSON object
//            val obj = JSONObject(getJSONFromAssets()!!)
//            // fetch JSONArray named users by using getJSONArray
//            val torquesArray = obj.getJSONArray("users")
//            // Get the users data using for loop i.e. id, name, email and so on
//
//            for (i in 0 until torquesArray.length()) {
//                // Create a JSONObject for fetching single User's Data
//                val torque = torquesArray.getJSONObject(i)
//                // Fetch id store it in variable
//                val id = torque.getInt("id")
//                val name = torque.getString("name")
//                val email = torque.getString("email")
//                val gender = torque.getString("gender")
//                val weight = torque.getDouble("weight")
//                val height = torque.getInt("height")
//
//                // create a object for getting phone numbers data from JSONObject
//                val phone = torque.getJSONObject("phone")
//                // fetch mobile number
//                val mobile = phone.getString("mobile")
//                // fetch office number
//                val office = phone.getString("office")
//
//                // Now add all the variables to the data model class and the data model class to the array list.
//                val torqueDetails =
//                    Torque(id, name, email, gender, weight, height, mobile, office)
//
//                // add the details in the list
//                torquesList.add(torqueDetails)
//            }
//        } catch (e: JSONException) {
//            //exception
//            e.printStackTrace()
//        }
    }

    private fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val torquesJSONFile = context.assets.open("Users.json")
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