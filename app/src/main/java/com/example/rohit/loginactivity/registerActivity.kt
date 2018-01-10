package com.example.rohit.loginactivity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View

import kotlinx.android.synthetic.main.activity_register.*

class registerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

    }

        fun backEvent (view: View){
            val intent = Intent(this@registerActivity,MainActivity::class.java);
            startActivity(intent);
        }





}
