package com.example.salesforcescanner

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson


class ScanDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_detail)

        //Create Contact object and add to Shared Preferences
        var contact = Contact(intent.getStringExtra(SCAN_RESULT),intent.getStringExtra(SCAN_ID));


        // Set fields on view
        val codeValue: TextView = findViewById<TextView>(R.id.codeValue)
        codeValue.text = contact.name
        val scanDateValue: TextView = findViewById<TextView>(R.id.scanDateValue)
        scanDateValue.text = contact.date
        val scanIdValue: TextView = findViewById<TextView>(R.id.scanIdValue)
        scanIdValue.text = contact.id
        val backBtn = findViewById<Button>(R.id.backButton)



        // Add intent on button to go back to main screen
        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
            }
            startActivity( intent)
        }

    }




}