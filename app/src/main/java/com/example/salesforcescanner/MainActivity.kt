package com.example.salesforcescanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent;
import android.widget.Toast;
import android.widget.Button;
import android.widget.TextView

import com.google.zxing.integration.android.IntentIntegrator;

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val scanCodeBtn = findViewById<Button>(R.id.button_scan_code)
        scanCodeBtn.setOnClickListener {
            IntentIntegrator(this).initiateScan()
            //Toast.makeText(this, SalesforceApi.testCall(), Toast.LENGTH_LONG).show()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                val scanOutput: TextView = findViewById(R.id.scanOutput)
                scanOutput.text = result.contents
            }
        }
    }
}