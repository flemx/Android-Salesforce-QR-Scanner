package com.example.salesforcescanner


import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
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
import com.google.gson.Gson
import com.google.zxing.integration.android.IntentIntegrator


const val SCAN_RESULT = "com.scanAnything.SCAN_RESULT"
const val SCAN_ID = "com.scanAnything.SCAN_ID"



class ScanFragment:Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_scan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


         val scanCodeBtn = view.findViewById<ImageButton>(R.id.tapToScan)
            scanCodeBtn.setOnClickListener {
                IntentIntegrator.forSupportFragment(this).initiateScan()
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
                        // Create Contact from API result and open ScanDetail page with Contact details
                       var contact = Contact(response,result.contents);
                        addContact(contact);
                        val intent = Intent(this.activity, ScanDetail::class.java).apply {
                            putExtra(SCAN_RESULT, contact.name)
                            putExtra(SCAN_ID, contact.id)
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


    /**
     * Adding contact to shared preferences
     */
    fun addContact(contact: Contact){
        val gson = Gson()
        val json = gson.toJson(contact)
        // Save functionality in storage
        var prefs = activity?.getSharedPreferences(getString(R.string.SHARED_PREF_NAME), Context.MODE_PRIVATE)
        var contacts = prefs?.getStringSet(getString(R.string.CONTACT_LIST),setOf())?.toMutableSet()
        if (contacts != null) {
            contacts.add(json)
        }
        if (prefs != null) {
            prefs.edit().putStringSet(getString(R.string.CONTACT_LIST), contacts).apply()
        }

    }



}