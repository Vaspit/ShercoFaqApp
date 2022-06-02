package com.example.shercofaqapp.utils

import android.content.Context
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.nio.charset.Charset

class JSONReader(private val context: Context) {

    fun getJSONFromAssets(fileName: String): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val torquesJSONFile = context.assets.open(fileName)
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

