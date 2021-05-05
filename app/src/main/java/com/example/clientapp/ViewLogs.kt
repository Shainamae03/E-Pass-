package com.example.clientapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ViewLogs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_logs)

        val back2_btn = findViewById(R.id.back2_btn) as Button

        back2_btn.setOnClickListener {
            startActivity(Intent(this@ViewLogs, ClientPage::class.java))
        }
    }
}