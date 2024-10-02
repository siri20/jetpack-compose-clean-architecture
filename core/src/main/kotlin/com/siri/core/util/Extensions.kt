package com.siri.core.util

import android.content.Context
import android.widget.Toast

/** 5/12/2023. */

object Extensions {
    fun Context.myToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}