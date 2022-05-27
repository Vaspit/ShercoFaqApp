package com.example.shercofaqapp.utils

import android.content.Context
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