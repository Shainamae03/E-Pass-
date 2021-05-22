package com.example.clientapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.TextUtils
import android.widget.*
import android.widget.Toast.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    var databaseReference : DatabaseReference? = null
    var database: FirebaseDatabase? = null

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        login()
    }

    private fun login(){

        val AccessName = findViewById<EditText>(R.id.AccessName)
        val code = findViewById<EditText>(R.id.code)
        val login = findViewById<Button>(R.id.login)
        val help = findViewById(R.id.help) as ImageView

       login.setOnClickListener {
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
                        auth = FirebaseAuth.getInstance()
                        database = FirebaseDatabase.getInstance()
                        val currentUserAdmin = auth.currentUser
                        val RegisteredUserID = currentUserAdmin.getUid()

                        databaseReference = database?.reference!!.child("ClientDb").child(RegisteredUserID)
                        if (currentUserAdmin.isEmailVerified) {
                            databaseReference?.addValueEventListener(object : ValueEventListener {
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    val ClientDb = snapshot.child("as").value.toString()
                                    if (ClientDb.equals("Client")) {
                                        startActivity(Intent(this@MainActivity, ClientPage::class.java))

                                    } else {
                                        Toast.makeText(this@MainActivity, "Login failed", Toast.LENGTH_LONG)
                                                .show()
                                    }

                                }

                                override fun onCancelled(error: DatabaseError) {
                                    TODO("Not yet implemented")
                                }
                            })
                        } else {
                            makeText(this@MainActivity, "Login failed", LENGTH_LONG)
                                    .show()
                        }
                    }else {
                        makeText(this@MainActivity, "Login failed! Please verfu your email", LENGTH_LONG)
                            .show()
                    }
                    val maxLength = 10
                    val filters = arrayOfNulls<InputFilter>(1)
                    filters[0] = LengthFilter(maxLength)
                    code.setFilters(filters)
                }



        }

        help.setOnClickListener {
            startActivity(Intent(this@MainActivity, HelpActivity::class.java))
        }
    }

}
