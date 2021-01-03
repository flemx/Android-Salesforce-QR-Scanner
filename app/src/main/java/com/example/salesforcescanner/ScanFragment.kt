package com.example.salesforcescanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


import android.content.Intent
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.RequestQueue
import com.google.zxing.integration.android.IntentIntegrator
import java.text.SimpleDateFormat
import java.util.*

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class ScanFragment:Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //        val scanValue = intent.extras?.getString("SCAN_RESULT")
//
//        val scanDate = intent.extras?.getString("SCAN_DATE")
//
//
//        val codeValue: TextView = findViewById<TextView>(R.id.codeValue)
//        val scanDateValue: TextView = findViewById<TextView>(R.id.scanDateValue)

    }



    /**
     * Method runs after QR Code is scanned and will call REST API to retrieve contact information
     */
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
//        if (result != null) {
//            if (result.contents == null) {
//                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
//            } else {
//
//                //Use volley for API request
//                val queue = Volley.newRequestQueue(this)
//                val url = "https://salesforce-qr-contacts01.herokuapp.com/contact/" + result.contents
//                // Request a string response from the provided URL.
//                val stringRequest = StringRequest(
//                    Request.Method.GET, url,
//                    Response.Listener<String> { response ->
//                        Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
//                        var date = Date()
//                        val formatter = SimpleDateFormat("MMM dd yyyy HH:mma")
//                        val scanDate: String = formatter.format(date)
//                        // Open the ScanDetail activity and pass through the result of the API call
//                        val intent = Intent(this, ScanDetail::class.java).apply {
//                            putExtra(SCAN_RESULT, response)
//                            putExtra(SCAN_DATE, scanDate)
//                        }
//                        startActivity( intent)
//                    },
//                    Response.ErrorListener {  Toast.makeText(this, "That didn't work!", Toast.LENGTH_LONG).show()  })
//
//                queue.add(stringRequest)
//
//
//            }
//        }
//    }

}