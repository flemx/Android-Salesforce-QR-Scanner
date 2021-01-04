package com.example.salesforcescanner


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

const val SCAN_RESULT = "com.scanAnything.SCAN_RESULT"
const val SCAN_ID = "com.scanAnything.SCAN_ID"
const val SCAN_PAGE = "com.scanAnything.SCAN_PAGE"

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Check if previous page was from List or Scan page
        val scanFragment=ScanFragment()
        val contactsFragment=ContactsFragment()

        var returnType = intent.getStringExtra(SCAN_PAGE)
        if(returnType == "LIST"){
           // val bottomView = bottomNavigationView.findViewById(R.id.contactsItem)
            setCurrentFragment(contactsFragment)
        }
        if(returnType == "SCAN"){
            setCurrentFragment(scanFragment)
        }
        else{
            setCurrentFragment(scanFragment)
        }


        bottomNavigationView.setOnNavigationItemSelectedListener {

            when(it.itemId){
                R.id.scanItem->setCurrentFragment(scanFragment)
                R.id.contactsItem->setCurrentFragment(contactsFragment)
            }
            true
        }


    }



    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()

        }



}