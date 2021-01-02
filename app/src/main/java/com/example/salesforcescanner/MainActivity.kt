package com.example.salesforcescanner

import android.content.Intent
import android.os.Bundle
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


const val SCAN_RESULT = "com.scanAnything.SCAN_RESULT"
const val SCAN_DATE = "com.scanAnything.SCAN_DATE"

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val scanCodeBtn = findViewById<ImageButton>(R.id.tapToScan)
        scanCodeBtn.setOnClickListener {
            //IntentIntegrator(this).initiateScan()

        }
    }







    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {



                //Volley for API request
                val queue = Volley.newRequestQueue(this)
                val url = "https://salesforce-qr-contacts01.herokuapp.com/contact" + result.contents
                // Request a string response from the provided URL.
                val stringRequest = StringRequest(
                    Request.Method.GET, url,
                    Response.Listener<String> { response ->
                        // Display the first 500 characters of the response string.
                        val obj = response
                        //textView.text = response

                        Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                        var date = Date()
                        val formatter = SimpleDateFormat("MMM dd yyyy HH:mma")
                        val scanDate: String = formatter.format(date)

                        val intent = Intent(this, ScanDetail::class.java).apply {
                            putExtra(SCAN_RESULT, response)
                            putExtra(SCAN_DATE, scanDate)
                        }
                        startActivity( intent)
                    },
                    Response.ErrorListener { textView.text = "That didn't work!" })

                // Add the request to the RequestQueue.
                queue.add(stringRequest)

            }
        }
    }
}