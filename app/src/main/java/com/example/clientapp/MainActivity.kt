package com.example.clientapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateUtils.LENGTH_LONG
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.*
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var AccessName = findViewById(R.id.AccessName) as EditText
        var code = findViewById(R.id.code) as EditText
        var button = findViewById(R.id.button) as Button
        var button2 = findViewById(R.id.button2) as Button

        button.setOnClickListener {
            val Access_Name = AccessName.text;
            val Access_code = code.text;
            startActivity(Intent(this@MainActivity, ClientPage::class.java))

        }
        button2.setOnClickListener {
            finishAffinity()
        }
    }
}

