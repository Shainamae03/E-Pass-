package com.example.clientapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth



class Menu : AppCompatActivity() {

    lateinit var authAdmin: FirebaseAuth
    lateinit var toggle: ActionBarDrawerToggle
    private val logfragment =  logFragment()
    private val qrfragment =  qrFragment()
    private val otherfragment =  otherFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        getSupportActionBar()?.hide();
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        replaceFragment(qrfragment)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomnavigation)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.logs_fragment -> {
                    val intent = Intent(this@Menu, ViewLogs::class.java)
                    startActivity(intent)
                }
                R.id.other_fragment -> replaceFragment(otherfragment)
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment){
        if(fragment !=null){
            val transaction = supportFragmentManager.beginTransaction()
            val frameLayout = findViewById<FrameLayout>(R.id.container)
            transaction.replace(R.id.container,fragment)
            transaction.commit()
        }

    }

  /*  override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
        {
            return true
        }
        return super.onOptionsItemSelected(item)
    }*/
}