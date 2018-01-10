package com.example.rohit.loginactivity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }



    fun onClickEvent(view : View){

        val intent = Intent(this@MainActivity,registerActivity::class.java);
        startActivity(intent);
    }



}


