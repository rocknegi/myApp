package com.example.rohit.loginactivity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun registerIntent(view : View){
        Snackbar.make(view, "Let's Register", Snackbar.LENGTH_SHORT)
        val intent = Intent(this@MainActivity, registerActivity::class.java)
        startActivity(intent)
    }

}


