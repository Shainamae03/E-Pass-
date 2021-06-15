package com.example.clientapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class question3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question3)
        val button = findViewById<Button>(R.id.back2)
        button.setOnClickListener {
            val intent = Intent(this, HelpActivity::class.java)
            startActivity(intent)
    }
}}