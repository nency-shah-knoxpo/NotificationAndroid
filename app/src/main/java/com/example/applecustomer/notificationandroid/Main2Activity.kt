package com.example.applecustomer.notificationandroid

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
       // supportActionBar?.setDisplayHomeAsUpEnabled(true)
        onNewIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val extras = intent?.extras
        if (intent?.extras != null) {
            val name = extras?.getString("name")
            val address = extras?.getString("address")
            val id = extras?.getString("id")
            findViewById<TextView>(R.id.name).text = name
            findViewById<TextView>(R.id.address).text = address

            findViewById<TextView>(R.id.id).text = id
        }

    }

}
