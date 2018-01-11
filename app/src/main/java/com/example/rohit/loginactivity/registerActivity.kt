package com.example.rohit.loginactivity

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject

class registerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

    }

    fun registerAccount (view: View){
        var registerTask = RegisterTask()
        registerTask.execute()
    }

    inner class RegisterTask : AsyncTask<Unit,Unit,Unit>(){
        override fun doInBackground(vararg params: Unit?) {
            var clientDetails = JSONObject()
            clientDetails.put("name",name.editText!!.text)
            clientDetails.put("username", username.editText!!.text)
            clientDetails.put("email", email.editText!!.text)
            clientDetails.put("password", password.editText!!.text)
            val body = RequestBody.create(JSON, clientDetails.toString())
            var client = OkHttpClient()
            var request = Request.Builder().url(baseUrl + registerClientUrl).post(body).build()
            var response = client.newCall(request).execute()
            if(response.code() == 201){
                Log.i("info",response.body()?.string())
                val intent = Intent(this@registerActivity,MainActivity::class.java)
                startActivity(intent)
            }else{
//                var responseBody = JSONObject(response.body().toString())
//                Toast.makeText(applicationContext, responseBody.getJSONObject("error").toString(), Toast.LENGTH_LONG)
                Log.i("info",response.body()?.string())
            }
        }
    }





}
