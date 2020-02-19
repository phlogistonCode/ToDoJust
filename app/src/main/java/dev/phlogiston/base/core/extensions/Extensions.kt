package dev.phlogiston.base.core.extensions

import android.content.Context
import android.content.res.Resources
import androidx.core.content.PermissionChecker

fun Boolean.toInt() = if (this) 1 else 0

fun checkPermission(context: Context, permission: String) =
    PermissionChecker.checkSelfPermission(context, permission) == PermissionChecker.PERMISSION_GRANTED

fun displayHeight() = Resources.getSystem().displayMetrics.heightPixels
fun displayWidth() = Resources.getSystem().displayMetrics.widthPixels