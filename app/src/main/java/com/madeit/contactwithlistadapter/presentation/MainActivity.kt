package com.madeit.contactwithlistadapter.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.madeit.contactwithlistadapter.data.Contact
import com.madeit.contactwithlistadapter.data.ContactsRepository
import com.madeit.contactwithlistadapter.databinding.ActivityMainBinding
import com.madeit.contactwithlistadapter.presentation.adapter.ContactsAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val contactsAdapter = ContactsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerViewSetUp()
        clickListeners()
    }

    private fun updateRecyclerView() {
        val allContacts = ContactsRepository.getContacts()
        contactsAdapter.submitList(ContactsRepository.getContacts())
        with(binding) {
            if (allContacts.isEmpty()) {
                noContactsImage.visibility = View.VISIBLE
                noContactsYet.visibility = View.VISIBLE
            } else {
                noContactsImage.visibility = View.INVISIBLE
                noContactsYet.visibility = View.INVISIBLE
            }
        }
    }

    private fun recyclerViewSetUp() {
        binding.contactsRecyclerView.apply {
            adapter = contactsAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun clickListeners() {
        with(binding) {
            btnAddNewContact.setOnClickListener {
                val intent = Intent(this@MainActivity, NewContactActivity::class.java)
                startActivity(intent)
            }
        }
        contactsAdapter.onItemDeleteClick = {
            ContactsRepository.deleteContact(it)
            updateRecyclerView()
        }
        contactsAdapter.onItemClick = {
            showContactDialog(it)
        }
    }

    private fun showContactDialog(contact: Contact) {
        AlertDialog.Builder(this)
            .setTitle("Name: ${contact.contactName}")
            .setMessage("Number - ${contact.contactNumber}")
            .setPositiveButton("Yes") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    override fun onResume() {
        super.onResume()
        updateRecyclerView()
    }

}