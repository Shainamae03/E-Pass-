package com.example.clientapp

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter


class ClientPage : AppCompatActivity() {


    private lateinit var database: DatabaseReference

    private lateinit var ivQRCode: ImageView
    private lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_page)

        var database = FirebaseDatabase.getInstance().reference


        var getdata = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var sb = StringBuilder()
                for (i in snapshot.children) {
                    var ClientDb = i.child("ClientDb").getValue()
                    val clientcode = ""
                    sb.append("${i.key}  $clientcode")
                }
                val  clientcode = findViewById<TextView>(R.id.clientcode) as TextView
               clientcode.setText(sb)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)


                var intent = intent
        val capt = intent.getStringExtra("result")
        val result_tv = findViewById<TextView>(R.id.clientcode)
        result_tv.text = capt
        ivQRCode = findViewById(R.id.qr)
        //qr code generate
        val data = result_tv.text.toString()
        if (data.isEmpty()) {
            Toast.makeText(this, "enter some data", Toast.LENGTH_SHORT).show()
        } else {
            val writer = QRCodeWriter()
            try {
                val bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 512, 512)
                val width = bitMatrix.width
                val height = bitMatrix.height
                val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
                for (x in 0 until width) {
                    for (y in 0 until height) {
                        bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                    }
                }
                ivQRCode.setImageBitmap(bmp)

            } catch (e: WriterException) {
            }

        }
    }


            }



