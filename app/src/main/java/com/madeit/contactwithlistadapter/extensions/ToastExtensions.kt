package com.madeit.contactwithlistadapter.extensions

import android.content.Context
import android.widget.Toast

fun Context.showToast(message: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}