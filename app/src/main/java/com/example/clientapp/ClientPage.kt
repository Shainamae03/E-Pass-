package com.example.clientapp

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter


class ClientPage : AppCompatActivity() {


    lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    private lateinit var ivQRCode: ImageView
    private lateinit var tv: TextView

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_page)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("ClientDb")

        val button4 = findViewById<Button>(R.id.button4) as Button
        val back3_btn = findViewById<Button>(R.id.back3_btn) as Button

        button4.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setCancelable(false)
            alertDialog.setMessage("Do you want to proceed?")
            alertDialog.setPositiveButton("yes", DialogInterface.OnClickListener { dialog, id ->
                startActivity(Intent(this@ClientPage, ViewLogs::class.java))
            })
            alertDialog.setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
            val alert = alertDialog.create();
            alert.setTitle("Approve Upon Entry")
            alert.show()
        }
        back3_btn.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setCancelable(false)
            alertDialog.setMessage("Do you want to proceed?")
            alertDialog.setPositiveButton("yes", DialogInterface.OnClickListener { dialog, id ->
                startActivity(Intent(this@ClientPage, Menu::class.java))
            })
            alertDialog.setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
            val alert = alertDialog.create();
            alert.setTitle("Approve Upon Entry")
            alert.show()
        }
        var database = FirebaseDatabase.getInstance().reference

        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)
        val clientcode = findViewById<TextView>(R.id.clientcode)
        val firtname = findViewById<TextView>(R.id.firtname)
        val lastname = findViewById<TextView>(R.id.lastname)



        userreference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                clientcode.text = snapshot.child("clientcode").value.toString()
                firtname.text = snapshot.child("firtname").value.toString()
                lastname.text = snapshot.child("lastname").value.toString()
                ivQRCode = findViewById(R.id.qr)
                //qr code generate
                val data = clientcode.text.toString()
                if (data.isEmpty()) {
                    Toast.makeText(this@ClientPage, "no data", Toast.LENGTH_LONG).show()
                } else {
                    val writer = QRCodeWriter()
                    try {
                        val bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 512, 512)
                        val width = bitMatrix.width
                        val height = bitMatrix.height
                        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
                        for (x in 0 until width) {
                            for (y in 0 until height) {
                                bmp.setPixel(
                                    x,
                                    y,
                                    if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
                                )
                            }
                        }
                        ivQRCode.setImageBitmap(bmp)

                    } catch (e: WriterException) {
                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        var startTime = System.currentTimeMillis()

        //adding 24 hours milliseconds with current time of milliseconds to make it 24 hours milliseconds.

        //adding 24 hours milliseconds with current time of milliseconds to make it 24 hours milliseconds.
        var milliseconds =
            System.currentTimeMillis() + 86400000 // 24 hours = 86400000 milliseconds


        val cdt: CountDownTimer = object : CountDownTimer(milliseconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                startTime = startTime - 1
                val serverUptimeSeconds = (millisUntilFinished - startTime) / 1000

                // now you repalce txtViewHours,  txtViewMinutes, txtViewSeconds by your textView.
                val hoursLeft = String.format("%d:", serverUptimeSeconds % 86400 / 3600)
                val txtViewHours = findViewById(R.id.txtViewHours) as TextView
                txtViewHours.setText(hoursLeft)
                Log.d("hoursLeft", hoursLeft)
                val minutesLeft = String.format("%d.", serverUptimeSeconds % 86400 % 3600 / 60)
                val txtViewMinutes = findViewById(R.id.txtViewMinutes) as TextView
                txtViewMinutes.setText(minutesLeft)
                Log.d("minutesLeft", minutesLeft)
                val secondsLeft = String.format("%d", serverUptimeSeconds % 86400 % 3600 % 60)
                val txtViewSeconds = findViewById(R.id.txtViewSeconds) as TextView
                txtViewSeconds.setText(secondsLeft)
                Log.d("secondsLeft", secondsLeft)
            }

            override fun onFinish() {
            }
        }

        cdt.start()
    }
}
