package com.madeit.contactwithlistadapter.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madeit.contactwithlistadapter.data.Contact
import com.madeit.contactwithlistadapter.databinding.ItemContactBinding

class ContactsAdapter() :
    ListAdapter<Contact, ContactsAdapter.ContactsViewHolder>(ContactsDiffCallBack()) {

    var onItemDeleteClick: (contact: Contact) -> Unit = {}
    var onItemClick: (contact: Contact) -> Unit = {}

    private class ContactsDiffCallBack() : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val view = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ContactsViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) = with(binding) {
            contactFirstLetter.text = contact.contactName[0].toString().uppercase()
            contactNumber.text = contact.contactNumber

            btnDeleteContact.setOnClickListener {
                onItemDeleteClick(contact)
            }

            root.setOnClickListener {
                onItemClick(contact)
            }
        }
    }

}