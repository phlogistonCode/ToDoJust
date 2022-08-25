package dev.phlogiston.todojust.core.extensions

import android.graphics.Paint
import android.graphics.Typeface
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun TextView.setColor(@ColorRes colorRes: Int) {
    this.setTextColor(ContextCompat.getColor(context, colorRes))
}

fun TextView.bold() {
    this.typeface = Typeface.DEFAULT_BOLD
}

fun TextView.boldNormal() {
    this.typeface = Typeface.DEFAULT
}

fun TextView.underline() {
    if ((this.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG) > 0) {
        this.paintFlags = this.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    } else {
        this.paintFlags = this.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }
}