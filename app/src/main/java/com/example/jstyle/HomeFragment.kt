package com.example.jstyle

import android.content.Context
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection
import java.util.*


class HomeFragment : Fragment() {

    private val sharedPrefFile = "kotlinsharedpreference"

    private var token : String? = null
    val CLIENT_ID = "217532133026340"
    val CALLBACK_URL = "https://localhost/"



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val sharedPreferences: SharedPreferences = this.activity!!.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )





//        val editor: SharedPreferences.Editor = sharedPreferences.edit()
//        //nanti kene load dari database pastu kita set kat fun
//        editor.putString("token", "IGQVJYMzdUSUlLWktWaUNkWE5rVWJ0Y2dWREpYMjFPOEpNZAW5RQTZAtQXFmdGtfTGFabTAwZA05WdzE0X1U3MkVYZAEZAQSEFkTFNNVUh2VExGV2pQcEpLRkpUdzZAMTGxHWmxzNl8wYzBB")
//        editor.apply()

        val root = inflater.inflate(R.layout.fragment_home, container, false)


        val sharedMenValue = sharedPreferences.getBoolean("men", false)
        val sharedWomenValue = sharedPreferences.getBoolean("women", true)

        val currentTime: Long = Calendar.getInstance().timeInMillis


        Log.e("App", "TOKEN" + sharedPreferences.getString("token","no"))

//        JsonTime(this).execute()

        Log.e("App", "TOKEN" + sharedPreferences.getString("token","no"))


        //rasanya kene wat kat server/databse ni

//        //refresh Instagram token after 50 days, doc says 60, welp just in case  PROBABLY NEED PROPER TESTING
//        if (currentTime - sharedPreferences.getLong("time",0) == 4320000000){
//            JsonTime(this).execute()
//
//        }

        val db = Firebase.firestore

        db.collection("TOKEN").get().addOnSuccessListener { result ->
            for (document in result) {
                Log.e("TAG", "${document.id} => ${document.data.values}")
                token = document.get("token") as String?

                class JsonTask :
                    AsyncTask<Void?, Void?, JSONObject?>() {


                    override fun doInBackground(vararg params: Void?): JSONObject? {




                        val str =
                            "https://graph.instagram.com/me/media?fields=id,media_type,media_url,caption&access_token=$token"
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
                            val webView: WebView = root.webview
                            val webView1: WebView = root.webview1
                            val webView2: WebView = root.webview2
                            val settings: WebSettings = webView.settings
                            val settings1: WebSettings = webView1.settings
                            val settings2: WebSettings = webView2.settings
                            settings.loadWithOverviewMode = true;
                            settings.useWideViewPort = true;
                            settings.builtInZoomControls = false;
                            settings.displayZoomControls = false;

                            settings1.loadWithOverviewMode = true;
                            settings1.useWideViewPort = true;
                            settings1.builtInZoomControls = false;
                            settings1.displayZoomControls = false;

                            settings2.loadWithOverviewMode = true;
                            settings2.useWideViewPort = true;
                            settings2.builtInZoomControls = false;
                            settings2.displayZoomControls = false;




                            try {
                                var temp = 0
                                var strwomen = 0
                                var strwomen1= 0
                                var strwomen2= 0
                                var strmen= 0
                                var strmen1= 0
                                var strmen2= 0

                                if(sharedMenValue && !sharedWomenValue) {
                                    while (temp<response.getJSONArray("data").length()) {
                                        if (response.getJSONArray("data").getJSONObject(temp)
                                                .getString("caption").contains("jstylewomen", true)
                                        ) {
                                            Log.e("App", "SUCCESS" + response.getJSONArray("data").length() + strwomen)

//
                                            if (strwomen == 0) {
                                                webView.loadUrl(
                                                    response.getJSONArray("data").getJSONObject(temp)
                                                        .getString("media_url")
                                                )
                                                strwomen +=1
                                                temp += 1
                                            }else if (strwomen1 == 0)
                                            {
                                                webView1.loadUrl(
                                                    response.getJSONArray("data").getJSONObject(temp)
                                                        .getString("media_url")
                                                )
                                                strwomen1 +=1
                                                temp += 1
                                            }else if (strwomen2 == 0)
                                            {
                                                webView2.loadUrl(
                                                    response.getJSONArray("data").getJSONObject(temp)
                                                        .getString("media_url")
                                                )
                                                strwomen2 +=1
                                                temp += 1
                                            }
                                            else{
                                                temp += 1

                                            }

                                        }else
                                        {
                                            temp +=1
                                        }
//
//
                                    }
                                }else{
                                    while (temp<response.getJSONArray("data").length()) {
                                        if (response.getJSONArray("data").getJSONObject(temp)
                                                .getString("caption").contains("jstylemen", true)
                                        ) {
                                            Log.e("App", "SUCCESS" + response.getJSONArray("data").length() + strwomen)

//
                                            if (strwomen == 0) {
                                                webView.loadUrl(
                                                    response.getJSONArray("data").getJSONObject(temp)
                                                        .getString("media_url")
                                                )
                                                strwomen +=1
                                                temp += 1
                                            }else if (strwomen1 == 0)
                                            {
                                                webView1.loadUrl(
                                                    response.getJSONArray("data").getJSONObject(temp)
                                                        .getString("media_url")
                                                )
                                                strwomen1 +=1
                                                temp += 1
                                            }else if (strwomen2 == 0)
                                            {
                                                webView2.loadUrl(
                                                    response.getJSONArray("data").getJSONObject(temp)
                                                        .getString("media_url")
                                                )
                                                strwomen2 +=1
                                                temp += 1
                                            }
                                            else{
                                                temp += 1

                                            }

                                        }else
                                        {
                                            temp +=1
                                        }
//
//
                                    }

                                }



                            } catch (ex: JSONException) {
                                Log.e("App", "Failure", ex)
                            }
                        }
                    }


                }

                JsonTask().execute()

            }

        }
            .addOnFailureListener { exception ->
                Log.e("TAG", "Error getting documents.", exception)
            }











        return root
    }


}



