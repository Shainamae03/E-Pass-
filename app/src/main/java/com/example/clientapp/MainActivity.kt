package com.example.clientapp

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateUtils.LENGTH_LONG
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.*
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var AccessName = findViewById<EditText>(R.id.AccessName)
        var code = findViewById<EditText>(R.id.code)
        var button = findViewById<Button>(R.id.button)
        val help = findViewById(R.id.help) as TextView

        button.setOnClickListener {
            val Access_Name = AccessName.text
            val Access_code = code.text
            startActivity(Intent(this@MainActivity, ClientPage::class.java))

        }


        help.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setCancelable(false)
            alertDialog.setMessage("Do you want to proceed?")
            alertDialog.setPositiveButton("yes", DialogInterface.OnClickListener { dialog, id ->
                startActivity(Intent(this@MainActivity , HelpActivity::class.java))
            })
            alertDialog.setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
            val alert = alertDialog.create();
            alert.setTitle("Do you want to Exit?")
            alert.show()
        }
    }
}
