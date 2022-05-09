package com.example.shercofaqapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.shercofaqapp.model.SparePart
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import kotlin.reflect.typeOf

class SparePartsFragmentViewModel: ViewModel() {

    fun getSpareParts(
        currentSparePartAddress: String,
        currentSparePartType: String,
        currentSparePartName: String,
    ): ArrayList<SparePart> {

        val database = Firebase.database.reference
        val sparePartsArrayList = ArrayList<SparePart>()

        try {
            database.child("parts").child(currentSparePartAddress)
                .child(currentSparePartType)
//                .child(currentSparePartName)
                .get().addOnSuccessListener {
                    val gson = Gson()
                    val json = JSONObject(gson.toJson(it.value).toString())
                    Log.d("SPARE_PARTS", json.toString())

                    val sparePartsArray = json.getJSONArray(currentSparePartName)
//
                    Log.d("SPARE_PARTS", sparePartsArray.toString())

                    for (i in 0 until sparePartsArray.length()) {
                        val sparePartJSONObject = sparePartsArray.getJSONObject(i)
                        val sparePart = SparePart(
                            sparePartJSONObject.getString("sparePartName"),
                            sparePartJSONObject.getString("sparePartDescription"),
                            sparePartJSONObject.getString("sparePartLink"),
                            sparePartJSONObject.getInt("sparePartImage")
                        )

                        sparePartsArrayList.add(sparePart)
                    }

                }.addOnFailureListener{
                    Log.e("firebase", "Error getting data", it)
                }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        
        return sparePartsArrayList
    }

}