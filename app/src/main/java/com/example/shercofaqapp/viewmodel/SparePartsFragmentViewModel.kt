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

    private val sparePartsArrayList = arrayListOf<SparePart>()

    fun getSpareParts(
        currentSparePartAddress: String,
        currentSparePartType: String,
        currentSparePartName: String,
    ): ArrayList<SparePart> {

        fillTheArrayList(
            currentSparePartAddress,
            currentSparePartType,
            currentSparePartName,
            sparePartsArrayList
        )

        Log.d("SPARE_PARTS", "Before 'return': " + sparePartsArrayList.toString())

        return sparePartsArrayList
    }

    private fun fillTheArrayList(
        currentSparePartAddress: String,
        currentSparePartType: String,
        currentSparePartName: String,
        sparePartsArrayList: ArrayList<SparePart>) {

        val database = Firebase.database.reference

        try {
            database.child("parts").child(currentSparePartAddress)
                .child(currentSparePartType)
//                .child(currentSparePartName)
                .get().addOnSuccessListener {
                    val gson = Gson()
                    val json = JSONObject(gson.toJson(it.value).toString())

                    val sparePartsArray = json.getJSONArray(currentSparePartName)

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

                    Log.d("SPARE_PARTS", "Inside the loop:" + sparePartsArrayList.toString())

                }.addOnFailureListener{
                    Log.e("firebase", "Error getting data", it)
                }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

}