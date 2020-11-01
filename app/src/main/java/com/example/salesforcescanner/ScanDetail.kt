package com.example.salesforcescanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_scan_detail.*

class ScanDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_detail)


        val scanValue = intent.getStringExtra(SCAN_RESULT)
        val scanDate = intent.getStringExtra(SCAN_DATE)


        val codeValue: TextView = findViewById<TextView>(R.id.codeValue)
        codeValue.text = scanValue
        val scanDateValue: TextView = findViewById<TextView>(R.id.scanDateValue)
        scanDateValue.text = scanDate

    }

//    override fun onStart() {
//        super.onStart()
//
//        val scanValue = intent.extras?.getString("SCAN_RESULT")
//
//        val scanDate = intent.extras?.getString("SCAN_DATE")
//
//
//        val codeValue: TextView = findViewById<TextView>(R.id.codeValue)
//        val scanDateValue: TextView = findViewById<TextView>(R.id.scanDateValue)
//
//
//
//    }

}