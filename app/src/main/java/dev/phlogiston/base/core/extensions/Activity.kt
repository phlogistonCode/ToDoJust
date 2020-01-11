package dev.phlogiston.base.core.extensions

import android.app.Activity
import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dev.phlogiston.base.R


inline fun <reified T : ViewModel> AppCompatActivity.viewModel(
    factory: ViewModelProvider.Factory
): T {
    return ViewModelProviders.of(this, factory)[T::class.java]
}

fun Activity.checkPermission(permission: String) = checkPermission(this, permission)

fun Activity.getStringArray(@ArrayRes id: Int) = resources.getStringArray(id)

fun Activity.showCustomDialog(
    @StringRes title: Int? = null,
    @StringRes message: Int? = null,
    positive: () -> Unit = {},
    negative: () -> Unit = {},
    @StringRes positiveButton: Int? = android.R.string.ok,
    @StringRes negativeButton: Int? = null,
    @ColorRes positiveColor: Int? = R.color.colorPrimary,
    @ColorRes negativeColor: Int? = R.color.colorAccent
) {
    val dialogListener = android.content.DialogInterface.OnClickListener { _, which ->
        when (which) {
            android.content.DialogInterface.BUTTON_POSITIVE -> {
                positive.invoke()
            }

            android.content.DialogInterface.BUTTON_NEGATIVE -> {
                negative.invoke()
            }
        }
    }

    val dialogBuilder = androidx.appcompat.app.AlertDialog.Builder(this)
    title?.let { dialogBuilder.setTitle(getString(it)) }
    message?.let { dialogBuilder.setMessage(getString(it)) }
    positiveButton?.let { dialogBuilder.setPositiveButton(getString(it), dialogListener) }
    negativeButton?.let { dialogBuilder.setNegativeButton(getString(it), dialogListener) }
    val dialog = dialogBuilder.create()

    dialog.setOnShowListener {
        negativeColor?.let {
            dialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(ContextCompat.getColor(this, it))
        }
        positiveColor?.let {
            dialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE)
                .setTextColor(ContextCompat.getColor(this, it))
        }
    }

    dialog.show()
}