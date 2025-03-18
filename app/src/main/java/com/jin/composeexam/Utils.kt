package com.jin.composeexam

import android.content.Context
import android.widget.Toast

fun Context.showToast(msg: String, isLongDuration: Boolean = false) {
    val duration = if (isLongDuration) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    Toast.makeText(this, msg, duration).show()
}