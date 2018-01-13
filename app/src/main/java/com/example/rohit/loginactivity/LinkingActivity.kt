package com.example.rohit.loginactivity

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_linking.*
import okhttp3.*
import org.json.JSONObject

class LinkingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linking)
        val linkTask = LinkTask()
        linkTask.execute()
    }

    fun linkAdmin(view: View){
        val linkTask = LinkTask()
        linkTask.execute()
    }

    inner class LinkTask: AsyncTask<Unit,Unit,Unit>(){
        override fun doInBackground(vararg params: Unit?) {
            progress.visibility = View.VISIBLE
            var client = OkHttpClient()
            val url = HttpUrl.Builder()
                    .scheme(scheme)
                    .host(baseUrl)
                    .addEncodedPathSegments(adminIdUrl)
                    .addQueryParameter("id_code",id_code.editText!!.text.toString())
                    .build()
            val request = Request.Builder().url(url).build()
            var response: Response? = null
            try{
                response = client.newCall(request).execute()
            }catch (exc: Exception){
                exc.printStackTrace()
            }
            if(response != null){
                if(response.code() == 200){
                    Log.i("info","HERE1")
                    info.visibility = View.GONE
                    val admin_id = JSONObject(response.body()?.string()).getString("admin_id")
                    Log.i("info","ADMIN ID: $admin_id")
                    val URL = HttpUrl.Builder()
                            .scheme(scheme)
                            .host(baseUrl)
                            .addEncodedPathSegments(linkAdminUrl)
                            .build()
                    var json = JSONObject()
                    json.put("username", "test")
                    json.put("password", password.editText!!.text.toString())
                    json.put("id_code", id_code.editText!!.text.toString())
                    json.put("admin_id", admin_id)
                    Log.i("info", "body: ${json.toString()}")
                    Log.i("info","url: ${URL.toString()}")
                    var body = RequestBody.create(JSON, json.toString())
                    val request2 = Request.Builder().url(URL).post(body).build()
                    var response2: Response? = null
                    try{
                        response2 = client.newCall(request2).execute()
                    }catch (exc: Exception){
                        exc.printStackTrace()
                    }
                    if(response2 != null){
                        if(response2.code() == 200){
                            Log.i("info","Yipee")
                        }
                    }

                }else if(response.code() == 404){
                    info.visibility = View.VISIBLE
                    info.text = "Wrong ID CODE entered"
                    Log.i("info","HERE2")
                }
            }else{
                Log.i("info","HERE3")
                info.text = "Response was null"
            }
            Log.i("info","HERE4")
            for(i in 1..1000000000){}
        }

        override fun onPostExecute(result: Unit?) {
            progress.visibility = View.GONE
        }
    }

}
