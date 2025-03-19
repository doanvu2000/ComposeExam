package com.jin.composeexam.util

import android.content.Context
import android.widget.Toast
import java.io.IOException
import java.io.InputStream

fun Context.showToast(msg: String, isLongDuration: Boolean = false) {
    val duration = if (isLongDuration) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    Toast.makeText(this, msg, duration).show()
}

fun Context.loadJsonFromAsset(path: String): String? {
    var json: String? = null
    try {
        val ios: InputStream = assets.open(path)
        val size = ios.available()
        val buffer = ByteArray(size)
        ios.read(buffer)
        ios.close()
        json = String(buffer, Charsets.UTF_8)
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return json
}