package com.example.clientapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog

class HelpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        val back_btn = findViewById(R.id.back_btn) as Button
        val faq1 = findViewById<ImageButton>(R.id.question1)
        val faq2 = findViewById<ImageButton>(R.id.question2)
        val faq3 = findViewById<ImageButton>(R.id.question3)
        val faq4 = findViewById<ImageButton>(R.id.question4)




        back_btn.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setCancelable(false)
            alertDialog.setMessage("Do you want to proceed?")
            alertDialog.setPositiveButton("yes", DialogInterface.OnClickListener { dialog, id ->
                finish()
            })
            alertDialog.setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
            val alert = alertDialog.create();
            alert.setTitle("Do you want to Exit?")
            alert.show()
        }

        faq1.setOnClickListener{
            val intent = Intent(this, question1::class.java)
            startActivity(intent)
        }
        faq2.setOnClickListener{
            val intent = Intent(this, question2::class.java)
            startActivity(intent)
        }
        faq3.setOnClickListener{
            val intent = Intent(this, question3::class.java)
            startActivity(intent)
        }
        faq4.setOnClickListener{
            val intent = Intent(this, question4::class.java)
            startActivity(intent)
        }
    }
}