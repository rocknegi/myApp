package com.example.rohit.loginactivity

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
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val checkForLogin = CheckForLogin()
            checkForLogin.execute()
    }



    inner class CheckForLogin : AsyncTask<Unit, Unit, Unit>() {
        override fun doInBackground(vararg params: Unit?) {
            var sharedPref = applicationContext.getSharedPreferences(clientPreferenceFileKey, Context.MODE_PRIVATE)
            if (sharedPref.getBoolean("logged_in", false)) {
                var intent = Intent(this@MainActivity, HomeActivity::class.java)
                intent.putExtra("response", "Welcome back, " + sharedPref.getString("name", "Something's wrong"))
                startActivity(intent)
            }
        }

//        override fun onPreExecute() {
//            progress.visibility = View.VISIBLE
//        }

        override fun onPostExecute(result: Unit?) {
            progress.visibility = View.GONE
        }
    }

    fun registerIntent(view: View) {
        Snackbar.make(view, "Let's Register", Snackbar.LENGTH_SHORT)
        val intent = Intent(this@MainActivity, registerActivity::class.java)
        startActivity(intent)
    }

    fun login(view: View) {
        var loginTask = LoginTask()
        loginTask.execute()
    }

    inner class LoginTask : AsyncTask<String, Unit, Unit>() {
        var success = false
        override fun doInBackground(vararg params: String?) {
            var json = JSONObject()
            json.put("username", username.editText!!.text)
            json.put("password", password.editText!!.text)
            var jsonString = json.toString()
            var body = RequestBody.create(JSON, jsonString)
            var client = OkHttpClient()
            var request = Request.Builder().url(baseUrl + loginClientUrl).post(body).build()
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
                    success = true
                    saveClientPreferences(jsonData!!)
                    var intent = Intent(this@MainActivity, HomeActivity::class.java)
                    val infoObj = JSONObject(jsonData)
                    intent.putExtra("response", "Welcome, ${infoObj.getJSONObject("client").getString("name")}")
                    startActivity(intent)
                } else {
                    Log.i("info", jsonData)
                }
            }else{
                Log.i("info", "The response was null")
            }
        }

        override fun onPostExecute(result: Unit?) {
            if(!success){
                Toast.makeText(applicationContext, "Network Error", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun saveClientPreferences(response: String) {
        val responseObj = JSONObject(response)
        val clientObj = responseObj.getJSONObject("client")
        var sharedPref = applicationContext.getSharedPreferences(clientPreferenceFileKey, Context.MODE_PRIVATE)
        var editor = sharedPref.edit()
        val keys = clientObj.keys()
        while (keys.hasNext()) {
            val key = keys.next() as String
            editor.putString(key, clientObj.getString(key))
            Log.i("info", key + " : " + clientObj.getString(key))
        }
        editor.putBoolean("logged_in", true)
        editor.commit()
    }

}


