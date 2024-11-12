package com.madeit.contactwithlistadapter.data

object ContactsRepository {

    private val allContacts = mutableListOf<Contact>()

    fun addContact(contact: Contact) {
        allContacts.add(contact)
    }

    fun getContacts(): List<Contact> {
        return allContacts.toList()
    }

    fun deleteContact(contact: Contact) {
        allContacts.remove(contact)
    }

}