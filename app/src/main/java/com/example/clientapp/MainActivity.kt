package com.example.clientapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateUtils.LENGTH_LONG
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.*
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var AccessName = findViewById<EditText>(R.id.AccessName)
        var code = findViewById<EditText>(R.id.code)
        var button = findViewById<Button>(R.id.button)
        var button2 = findViewById<Button>(R.id.button2)
        val help = findViewById(R.id.help) as TextView

        button.setOnClickListener {
            val Access_Name = AccessName.text
            val Access_code = code.text
            startActivity(Intent(this@MainActivity, ClientPage::class.java))

        }
        button2.setOnClickListener {
            finishAffinity()
        }
        help.setOnClickListener {
            startActivity(Intent( this@MainActivity, HelpActivity::class.java))
        }
    }
}
