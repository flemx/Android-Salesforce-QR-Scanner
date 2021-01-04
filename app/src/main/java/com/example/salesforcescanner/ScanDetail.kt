package com.example.salesforcescanner

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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

        //set event listeners on buttons
        val backBtn = findViewById<Button>(R.id.backButton)
        val delBtn = findViewById<Button>(R.id.deleteButton)


        // Add intent on button to go back to main screen
        backBtn.setOnClickListener {
            returnToPage()
        }

        // Delete contact from shared preferences
        delBtn.setOnClickListener {
            var prefs = this.getSharedPreferences(getString(R.string.SHARED_PREF_NAME), Context.MODE_PRIVATE)
            var contacts = prefs?.getStringSet(getString(R.string.CONTACT_LIST),setOf())?.toMutableSet()
            val gson = Gson()
            val json = gson.toJson(contact)
            if (contacts != null) {
                contacts.remove(json)
            }
            Toast.makeText(this, contact.id + " removed!", Toast.LENGTH_LONG).show()
            prefs.edit().putStringSet(getString(R.string.CONTACT_LIST), contacts).apply()
            returnToPage();
        }




    }


    private fun returnToPage(){
        var returnType = intent.getStringExtra(SCAN_PAGE)

        if(returnType == "LIST"){
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra(SCAN_PAGE, "LIST")
            }
            startActivity( intent)
        }
        if(returnType == "SCAN"){
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra(SCAN_PAGE, "SCAN")
            }
            startActivity( intent)
        }
    }




}