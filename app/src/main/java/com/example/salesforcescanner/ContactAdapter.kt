package com.example.salesforcescanner

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contacts_row.view.*

class ContactAdapter(val contacts: List<Contact>) : RecyclerView.Adapter<ContactAdapter.ContactsHolder>() {


    class ContactsHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener{

        var view: View = v
        var contact : Contact = Contact("","")


        init {
            v.setOnClickListener(this)
        }

        fun bindTodo(contact:Contact){
            this.contact = contact
            view.textView.text = contact.name
        }

        override fun onClick(v: View?) {
            val intent = Intent(view.context, ScanDetail::class.java).apply {
                putExtra(SCAN_RESULT, contact.name)
                putExtra(SCAN_ID, contact.id)
                putExtra(SCAN_PAGE, "LIST")
            }
            startActivity( view.context, intent, null)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsHolder {
        return ContactsHolder(LayoutInflater.from(parent.context).inflate(R.layout.contacts_row, parent, false))
    }

    override fun getItemCount(): Int {
        return contacts.count();
    }

    override fun onBindViewHolder(holder: ContactsHolder, position: Int) {
        val name = contacts[position]
        holder.bindTodo(name)
    }

}