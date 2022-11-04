package dev.phlogiston.todojust.core.extensions

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible

fun View.getCenterAbsoluteX(): Int {
    val location = IntArray(2)
    getLocationOnScreen(location)
    return location[0] + width / 2
}

fun View.getCenterAbsoluteY(): Int {
    val location = IntArray(2)
    getLocationOnScreen(location)
    return location[1]
}

fun View.visible() {
    isVisible = true
}

fun View.gone() {
    isVisible = false
}

fun View.invisible() {
    isInvisible = true
}