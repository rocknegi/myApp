package com.example.rohit.loginactivity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View

class registerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

    }

    fun registerAccount (view: View){
        Snackbar.make(view, "Thank you for registering", Snackbar.LENGTH_SHORT)
        val intent = Intent(this@registerActivity,MainActivity::class.java)
        startActivity(intent)
    }





}
