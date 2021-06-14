package com.example.clientapp

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import android.widget.Toast.*
import androidx.appcompat.app.AlertDialog
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
        if(currentUser != null){
            startActivity(Intent(this@MainActivity, Menu::class.java))
            finish()
        }
        login()
    }

    var totalAttempts = 3
    private fun login() {

        val AccessName = findViewById<TextInputEditText>(R.id.Username)
        val code = findViewById<TextInputEditText>(R.id.adminpass)
        val imageButton = findViewById<ImageButton>(R.id.login)
        val help = findViewById<ImageButton>(R.id.help)
        val cancel_mainscreen = findViewById<Button>(R.id.cancel_mainscreen)
        val forgotpass = findViewById<Button>(R.id.forgotpass)

        forgotpass.setOnClickListener{
            startActivity(Intent( this@MainActivity, Changepass::class.java))
        }



        help.setOnClickListener {
            startActivity(Intent(this@MainActivity, HelpActivity::class.java))
        }
        cancel_mainscreen.setOnClickListener{
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setCancelable(false)
            alertDialog.setMessage("Do you want to exit?")
            alertDialog.setPositiveButton("yes", DialogInterface.OnClickListener { dialog, id ->
                finishAffinity()
            })
            alertDialog.setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
            val alert = alertDialog.create();
            alert.setTitle("Exit")
            alert.show()


        }
        imageButton.setOnClickListener {
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
                        if (totalAttempts != 0) {
                            if (it.isSuccessful) {
                                startActivity(Intent(this@MainActivity, ClientPage::class.java))
                                finish()

                            } else
                            makeText(this@MainActivity, "Number of attemps left: ${totalAttempts}", LENGTH_LONG).show()
                            totalAttempts--;

                    } else{
                            Toast.makeText(this, "Maximum number of attempts exceeded", Toast.LENGTH_SHORT)
                                        .show()
                        }


                            }

                        }
                    }

        }



