package com.example.clientapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.*
import android.widget.Toast.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth



class MainActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        login()
    }

    private fun login(){

        val AccessName = findViewById<TextInputEditText>(R.id.Username)
        val code = findViewById<TextInputEditText>(R.id.adminpass)
        val button = findViewById<Button>(R.id.login)
        val help = findViewById<ImageButton>(R.id.help)

        button.setOnClickListener {
            if (TextUtils.isEmpty(AccessName.text.toString())) {
                AccessName.setError("Please enter your email!")
                return@setOnClickListener
            } else if (TextUtils.isEmpty(code.text.toString())) {
                code.setError("Please enter password!")
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(
                AccessName.text.toString(),
                code.text.toString()
            )
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        startActivity(Intent(this@MainActivity, ClientPage::class.java))
                        finish()

                    } else {
                        makeText(this@MainActivity, "Login failed", LENGTH_LONG)
                            .show()
                    }
                }



        }

        help.setOnClickListener {
            startActivity(Intent( this@MainActivity, HelpActivity::class.java))
        }
    }

}
