package com.example.salesforcescanner

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_contacts.view.*


class ContactsFragment:Fragment() {

    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: ContactAdapter
    var viewVar: View? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.viewVar
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }


    /**
     *  Get saved contact JSON strings from Shared Preferences set and convert into a Contact list for the ContactAdapter RecycleView
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gson = Gson()
        var prefs = activity?.getSharedPreferences(getString(R.string.SHARED_PREF_NAME), Context.MODE_PRIVATE)
        var contactSet = prefs?.getStringSet(getString(R.string.CONTACT_LIST),setOf())?.toMutableSet()
        var contactList = mutableListOf<Contact>()

        if (contactSet != null) {
            for(item in contactSet){
                var newContact = gson.fromJson(item, Contact::class.java)
                contactList.add(newContact)
            }
        }
        layoutManager = LinearLayoutManager(activity)
        view.recyclerView.layoutManager = layoutManager
        if (contactList != null) {
            adapter = ContactAdapter(contactList)
        }else{
            var emptyList = mutableListOf<Contact>()
            emptyList.add(Contact("No Contacts added",""))
            adapter = ContactAdapter(emptyList)
        }
        view.recyclerView.adapter = adapter

    }



}