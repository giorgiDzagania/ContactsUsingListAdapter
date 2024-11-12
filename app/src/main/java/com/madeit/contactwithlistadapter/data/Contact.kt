package com.madeit.contactwithlistadapter.data

import java.util.UUID

data class Contact(
    val id: String = UUID.randomUUID().toString(),
    val contactName: String,
    val contactNumber: String
)
