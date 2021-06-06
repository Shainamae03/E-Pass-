package com.example.clientapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Menu : AppCompatActivity() {

    private val qrfragment =  qrFragment()
    private val logsfragment =  logFragment()
    private val otherfragment =  otherFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        replaceFragment(qrfragment)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomnavigation)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.qr_fragment -> {
                    val intent = Intent(this@Menu, ClientPage::class.java)
                    startActivity(intent)
                }
                R.id.logs_fragment -> replaceFragment(logsfragment)
                R.id.other_fragment -> replaceFragment(logsfragment)
            }
            true
        }

        /* val qrpasscode = findViewById(R.id.qrpass) as CardView
        val my_log = findViewById(R.id.my_log) as CardView
        val back = findViewById(R.id.back) as CardView

        qrpasscode.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setCancelable(false)
            alertDialog.setMessage("Do you want to proceed?")
            alertDialog.setPositiveButton("yes", DialogInterface.OnClickListener { dialog, id ->
                startActivity(Intent(this@Menu, ClientPage::class.java))
            })
            alertDialog.setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
            val alert = alertDialog.create();
            alert.setTitle("Approve Upon Entry")
            alert.show()
        }
        my_log.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setCancelable(false)
            alertDialog.setMessage("Do you want to proceed?")
            alertDialog.setPositiveButton("yes", DialogInterface.OnClickListener { dialog, id ->
                startActivity(Intent(this@Menu, ViewLogs::class.java))
            })
            alertDialog.setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
            val alert = alertDialog.create();
            alert.setTitle("Do you want to leave this page?")
            alert.show()
        }
        back.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setCancelable(false)
            alertDialog.setMessage("Do you want to proceed?")
            alertDialog.setPositiveButton("yes", DialogInterface.OnClickListener { dialog, id ->
                startActivity(Intent(this@Menu, MainActivity::class.java))
            })
            alertDialog.setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
            val alert = alertDialog.create();
            alert.setTitle("Do you want to exit?")
            alert.show()
        }
    }*/
    }
    private fun replaceFragment(fragment: Fragment){
        if(fragment !=null){
            val transaction = supportFragmentManager.beginTransaction()
            val frameLayout = findViewById<FrameLayout>(R.id.container)
            transaction.replace(R.id.container,fragment)
            transaction.commit()
        }
}}