package com.example.salesforcescanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent;
import android.media.Image
import android.widget.Toast;
import android.widget.Button;
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat

import com.google.zxing.integration.android.IntentIntegrator;
import java.text.SimpleDateFormat
import java.util.*

const val SCAN_RESULT = "com.scanAnything.SCAN_RESULT"
const val SCAN_DATE = "com.scanAnything.SCAN_DATE"

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val scanCodeBtn = findViewById<ImageButton>(R.id.tapToScan)
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
                var date = Date()
                val formatter = SimpleDateFormat("MMM dd yyyy HH:mma")
                val scanDate: String = formatter.format(date)

                val intent = Intent(this, ScanDetail::class.java).apply {
                    putExtra(SCAN_RESULT, result.contents)
                    putExtra(SCAN_DATE, scanDate)
                }
                startActivity( intent)

            }
        }
    }
}