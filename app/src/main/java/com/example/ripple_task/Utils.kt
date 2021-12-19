package com.example.ripple_task

import android.content.Context
import java.io.*
import java.util.*

object Utils {
     const val baseUrL : String = "https://api.github.com/"
     const val BUILD_MODE_MOCK = "mock"

    fun getDataFromJsonFile(context: Context, i: InputStream,searchw:String): String? {
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        var jsonString: String?=null
        if (searchw.lowercase(Locale.getDefault()).equals("joe")) {
            try {

                val reader: Reader = BufferedReader(InputStreamReader(i, "UTF-8"))
                var n: Int
                while (reader.read(buffer).also { n = it } != -1) {
                    writer.write(buffer, 0, n)
                }
            } finally {
                i.close()
            }
            jsonString = writer.toString()
        } else {
            jsonString = null
        }


        return jsonString
    } //Parsing data from json to DataClass
}