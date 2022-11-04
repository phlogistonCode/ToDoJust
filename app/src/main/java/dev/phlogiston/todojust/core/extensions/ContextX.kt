package dev.phlogiston.todojust.core.extensions

import android.content.Context
import android.content.res.Configuration
import android.os.Handler
import android.os.Looper

fun Context.isNightMode() =
    when (resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_YES -> true
        Configuration.UI_MODE_NIGHT_NO -> false
        Configuration.UI_MODE_NIGHT_UNDEFINED -> false
        else -> false
    }

fun postDelayed(pause: Long = 800, func: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed(func, pause)
}