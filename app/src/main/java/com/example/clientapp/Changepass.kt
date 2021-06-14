package com.example.clientapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class Changepass : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changepass)

        val btn_change_password = findViewById<Button>(R.id.btn_change_password)
        auth = FirebaseAuth.getInstance()
        btn_change_password.setOnClickListener {
            changePassword()
        }
            }
    private fun changePassword(){

        val et_current_password = findViewById<EditText>(R.id.et_current_password)
        val et_new_password = findViewById<EditText>(R.id.et_new_password)
        val et_confirm_password = findViewById<EditText>(R.id.et_current_password)

        if(et_current_password.text.isNotEmpty() &&
                et_new_password.text.isNotEmpty() &&
                et_confirm_password.text.isNotEmpty()){
        if (et_new_password.text.toString().equals(et_confirm_password.text.toString())) {
            val user = auth.currentUser
            if(user !=null) {
                val credential = EmailAuthProvider
                        .getCredential(user.email!!, et_current_password.text.toString())

        user?.reauthenticate(credential)
                ?.addOnCompleteListener{
                    if(it.isSuccessful) {
                        Toast.makeText( this, "Re-Aunthentication Success", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Re-Aunthentication Failed", Toast.LENGTH_SHORT).show()
                        user?.updatePassword(et_new_password.text.toString())
                                ?.addOnCompleteListener { task ->
                                    if (task.isSuccessful)
                                        Toast.makeText(this, "Password Change Successfully ",Toast.LENGTH_SHORT).show()
                                    auth.signOut()
                        }
                    }
                }
            }else {
                startActivity(Intent(this, Changepass::class.java))
                finish()
            }
        }else{
            Toast.makeText(this, "Password mismatching", Toast.LENGTH_SHORT).show()
        }
    }else {
            Toast.makeText(this, "Please Enter all fields.", Toast.LENGTH_SHORT)
        }



        }
            }
