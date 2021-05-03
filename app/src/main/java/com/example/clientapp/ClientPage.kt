package com.example.clientapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ClientPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_page)


        var button3 = findViewById(R.id.button3) as Button


        button3.setOnClickListener {
            startActivity(Intent(this@ClientPage, MainActivity::class.java))

        }
    }
}