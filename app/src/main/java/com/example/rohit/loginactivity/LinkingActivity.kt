package com.example.rohit.loginactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class LinkingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linking)
    }

    fun linkAdmin(view: View){
        Toast.makeText(this@LinkingActivity, "Waka Waka", Toast.LENGTH_LONG).show()
    }
}
