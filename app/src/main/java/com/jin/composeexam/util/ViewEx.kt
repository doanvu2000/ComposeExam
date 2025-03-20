package com.jin.composeexam.util

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput

fun Modifier.clickOutsideListener(listener: (() -> Unit)? = null): Modifier {
    pointerInput(Unit) {
        detectTapGestures(onTap = { listener?.invoke() })
    }
    return this
}