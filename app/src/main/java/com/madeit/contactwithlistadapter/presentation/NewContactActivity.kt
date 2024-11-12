package com.madeit.contactwithlistadapter.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.madeit.contactwithlistadapter.R
import com.madeit.contactwithlistadapter.data.Contact
import com.madeit.contactwithlistadapter.data.ContactsRepository
import com.madeit.contactwithlistadapter.databinding.ActivityNewContactBinding
import com.madeit.contactwithlistadapter.extensions.showToast
import com.madeit.contactwithlistadapter.presentation.adapter.ContactsAdapter

class NewContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewContactBinding
    private var contactsAdapter = ContactsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNewContact()
    }

    private fun createNewContact() {
        binding.btnCreate.setOnClickListener {
            val newContactName = binding.contactUsername.text.toString()
            val newContactNumber = binding.contactNumber.text.toString()
            if (checkInputValidation(newContactName, newContactNumber)) {
                ContactsRepository.addContact(
                    Contact(contactName = newContactName, contactNumber = newContactNumber))
                contactsAdapter.submitList(ContactsRepository.getContacts())
                finish()
            } else {
                showToast(R.string.not_valid_case)
            }
        }
    }

    private fun checkInputValidation(name: String, number: String): Boolean {
        val lettersOnlyRegex = "^(?=.*[a-zA-Z\u10A0-\u10FF])[a-zA-Z\u10A0-\u10FF ]+$".toRegex()
        return name.isNotEmpty() && number.isNotEmpty() &&
                name.matches(lettersOnlyRegex) && number.length >= 9
    }

}