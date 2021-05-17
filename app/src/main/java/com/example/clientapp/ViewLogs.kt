package com.example.clientapp

import android.annotation.TargetApi
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ViewLogs : AppCompatActivity() {

    private lateinit var dbref: DatabaseReference
    private lateinit var clientlog: RecyclerView
    private lateinit var clientArrayList: ArrayList<User>

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_logs)

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getSupportActionBar()?.hide();


        val backtohome = findViewById(R.id.backtohome) as Button
        backtohome.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setCancelable(false)
            alertDialog.setMessage("Do you want to proceed?")
            alertDialog.setPositiveButton("yes", DialogInterface.OnClickListener { dialog, id ->
                startActivity(Intent(this@ViewLogs, Menu::class.java))
            })
            alertDialog.setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
            val alert = alertDialog.create();
            alert.setTitle("Approve Upon Entry")
            alert.show()

        }


        clientlog = findViewById(R.id.clientlog)
        clientlog.layoutManager = LinearLayoutManager(this,)
        clientlog.setHasFixedSize(true)


        clientArrayList = arrayListOf()
        getUserData()


    }

    private fun getUserData() {
        dbref = FirebaseDatabase.getInstance().getReference("ClientDb")
        dbref.addValueEventListener(object : ValueEventListener {


            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(User::class.java)
                        clientArrayList.add(user!!)
                    }
                }
                clientlog.adapter = CHAdapter(clientArrayList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


}
