package com.example.salesforcescanner


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_scan.view.*
import java.text.SimpleDateFormat
import java.util.*

const val SCAN_RESULT = "com.scanAnything.SCAN_RESULT"
const val SCAN_DATE = "com.scanAnything.SCAN_DATE"



class ScanFragment:Fragment() {


    var viewVar: View? = null


//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//
//
//        return inflater.inflate(R.layout.fragment_scan, container, false)
//    }






    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_scan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


         val scanCodeBtn = view.findViewById<ImageButton>(R.id.tapToScan)
            scanCodeBtn.setOnClickListener {
                IntentIntegrator.forSupportFragment(this).initiateScan()

//                var date = Date()
//                val formatter = SimpleDateFormat("MMM dd yyyy HH:mma")
//                val scanDate: String = formatter.format(date)
//                val intent = Intent(getActivity(), ScanDetail::class.java).apply {
//                    putExtra(SCAN_RESULT, "Result Test")
//                    putExtra(SCAN_DATE, scanDate)
//                }
//                startActivity( intent)

            }


    }



    /**
     * Method runs after QR Code is scanned and will call REST API to retrieve contact information
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        println("RESULT IS:" + result.contents)

        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show()
            } else {

                //Use volley for API request
                val queue = Volley.newRequestQueue(getActivity())
                val url = "https://salesforce-qr-contacts01.herokuapp.com/contact/" + result.contents
                // Request a string response from the provided URL.
                val stringRequest = StringRequest(
                    Request.Method.GET, url,
                    Response.Listener<String> { response ->
                        Toast.makeText(getActivity(), "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                        var date = Date()
                        val formatter = SimpleDateFormat("MMM dd yyyy HH:mma")
                        val scanDate: String = formatter.format(date)
                        // Open the ScanDetail activity and pass through the result of the API call
                        val intent = Intent(getActivity(), ScanDetail::class.java).apply {
                            putExtra(SCAN_RESULT, response)
                            putExtra(SCAN_DATE, scanDate)
                        }
                        startActivity( intent)
                    },
                    Response.ErrorListener {
                        Toast.makeText(getActivity(), "That didn't work!", Toast.LENGTH_LONG).show()
                    })

                queue.add(stringRequest)


            }
        }


    }

}