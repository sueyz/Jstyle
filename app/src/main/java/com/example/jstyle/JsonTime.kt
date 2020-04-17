package com.example.jstyle

import android.content.Context
import android.content.SharedPreferences
import android.os.AsyncTask
import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection

class JsonTime(homeFragment: HomeFragment) :
    AsyncTask<Void?, Void?, JSONObject?>() {
    private val sharedPrefFile = "kotlinsharedpreference"


    private val sharedPreferences: SharedPreferences = homeFragment.context!!.getSharedPreferences(
        sharedPrefFile,
        Context.MODE_PRIVATE
    )

    override fun doInBackground(vararg params: Void?): JSONObject? {


        val str =
            "https://graph.instagram.com/refresh_access_token?grant_type=ig_refresh_token&access_token=" + sharedPreferences.getString(
                "token",
                "no"
            )
        val urlConn: URLConnection?
        var bufferedReader: BufferedReader? = null
        return try {
            val url = URL(str)
            urlConn = url.openConnection()
            bufferedReader =
                BufferedReader(InputStreamReader(urlConn.getInputStream()))
            val stringBuffer = StringBuffer()
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuffer.append(line)
            }
            JSONObject(stringBuffer.toString())
        } catch (ex: Exception) {
            Log.e("App", "yourDataTask", ex)
            null
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onPostExecute(response: JSONObject?) {


        if (response != null) {

            try {
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("token", response.getString("access_token"))
                editor.apply()


            } catch (ex: JSONException) {
                Log.e("App", "Failure", ex)
            }
        }
    }


}
