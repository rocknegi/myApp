package com.example.rohit.loginactivity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        response.text = intent.getStringExtra("response")
        checkForLinkedClient()
    }

    fun checkForLinkedClient(){
        val sharedPref = getSharedPreferences(clientPreferenceFileKey, Context.MODE_PRIVATE)
        if(sharedPref.getString("linked", "false") == "false"){
            info.text = "Please link your id with an Admin Account to USE this app."
            action_fab.setOnClickListener { v ->
                val intent = Intent(this@HomeActivity, LinkingActivity::class.java)
                startActivity(intent)
            }
        }else{
            action_fab.setOnClickListener { v ->
                Toast.makeText(this@HomeActivity, "Wohoooo", Toast.LENGTH_LONG).show()
            }
        }
    }
}
