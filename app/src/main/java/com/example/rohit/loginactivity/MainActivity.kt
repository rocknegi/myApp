package com.example.rohit.loginactivity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val checkForLogin = CheckForLogin(WeakReference<Context>(applicationContext), WeakReference<Activity>(this))
            checkForLogin.execute()
    }


    companion object {
        class CheckForLogin(private val weakContext: WeakReference<Context> ,private val weakActivity: WeakReference<Activity>) : AsyncTask<Unit, Unit, Unit>() {

            override fun doInBackground(vararg params: Unit?) {
                for(i in 1..1000000000){}
                val activity = weakActivity.get()
                val context = weakContext.get()
                if (getBoolean(context!!,"logged_in")) {       //Using getBoolean from Utils
                    val intent = Intent(context, HomeActivity::class.java)
                    intent.putExtra("response", "Welcome back, " + getString(context,"name", "Something's wrong"))
                    activity!!.startActivity(intent)
                    Log.i("info","SUCCESSFUL")
                }
            }

            override fun onPostExecute(result: Unit?) {
                weakActivity.get()!!.progress.visibility = View.GONE
            }
        }
        class LoginTask(private val weakContext: WeakReference<Context> ,private val weakActivity: WeakReference<Activity>) : AsyncTask<String, Unit, Unit>() {
            var success = true
            override fun doInBackground(vararg params: String?) {
                if(!isNetworkAvailable(weakActivity.get()!!)){
                    success = false
                    return
                }
                var json = JSONObject()
                json.put("username", weakActivity.get()!!.username.editText!!.text)
                json.put("password", weakActivity.get()!!.password.editText!!.text)
                var jsonString = json.toString()
                var body = RequestBody.create(JSON, jsonString)
                var client = OkHttpClient()
                val url = HttpUrl.Builder()
                        .scheme(scheme)
                        .host(baseUrl)
                        .addEncodedPathSegments(loginClientUrl)
                        .build()
                var request = Request.Builder().url(url).post(body).build()
                var response: Response? = null
                try {
                    response = client.newCall(request).execute()
                }catch (exc: Exception){
                    Log.i("error", exc.message)
                }
                if(response != null) {
                    val jsonObj = response.body()
                    var jsonData = jsonObj?.string()
                    if (response.code() == 200) {
                        saveClientPreferences(weakContext,jsonData!!)
                        var intent = Intent(weakActivity.get()!!, HomeActivity::class.java)
                        weakActivity.get()!!.startActivity(intent)
                    } else {
                        Log.i("info", jsonData)
                    }
                }else{
                    Log.i("info", "The response was null")
                }
            }

            override fun onPostExecute(result: Unit?) {
                Toast.makeText(weakContext.get(), "Network error", Toast.LENGTH_LONG)
            }
        }

        fun saveClientPreferences(weakContext: WeakReference<Context>, response: String) {
            val responseObj = JSONObject(response)
            val clientObj = responseObj.getJSONObject("client")
            var sharedPref = weakContext.get()!!.getSharedPreferences(clientPreferenceFileKey, Context.MODE_PRIVATE)
            var editor = sharedPref.edit()
            val keys = clientObj.keys()
            while (keys.hasNext()) {
                val key = keys.next() as String
                editor.putString(key, clientObj.getString(key))
                Log.i("info", key + " : " + clientObj.getString(key))
            }
            editor.putBoolean("logged_in", true)
            editor.apply()
        }
    }


    fun registerIntent(view: View) {
        Snackbar.make(view, "Let's Register", Snackbar.LENGTH_SHORT)
        val intent = Intent(this@MainActivity, registerActivity::class.java)
        startActivity(intent)
    }

    fun login(view: View) {
        if(true){
            val loginTask = LoginTask(WeakReference<Context>(applicationContext), WeakReference<Activity>(this))
            loginTask.execute()
        }else{
            Toast.makeText(applicationContext, "Network Error", Toast.LENGTH_LONG).show()
        }
    }



}


