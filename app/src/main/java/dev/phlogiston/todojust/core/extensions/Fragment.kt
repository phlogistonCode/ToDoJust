package dev.phlogiston.todojust.core.extensions

import android.content.DialogInterface
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.phlogiston.todojust.R

inline fun <reified T : ViewModel> Fragment.viewModel(
    factory: ViewModelProvider.Factory
): T {
    return ViewModelProvider(this, factory)[T::class.java]
}

inline fun <reified T : ViewModel> Fragment.activityViewModel(
    factory: ViewModelProvider.Factory
): T {
    return activity?.let {
        ViewModelProvider(it, factory)[T::class.java]
    } ?: throw Exception("no activity vm")
}

fun enableBtnIfNotEmptyEditText(
    button: View,
    vararg editText: EditText,
    needSymbols: Int = 1
) {
    editText.forEach {
        it.doOnTextChanged { _, _, _, _ -> checkETs(button, editText, needSymbols) }
    }
}

private fun checkETs(
    button: View,
    editText: Array<out EditText>,
    needSymbols: Int
) {
    var buttonEnabled = true
    editText.forEach {
        if (it.editableText.toString().length < needSymbols)
            buttonEnabled = false
    }
    button.isEnabled = buttonEnabled
}

fun enableBtnIfNotEmptyCheckBox(
    button: View,
    vararg checkBox: CheckBox
) {
    checkBox.forEach { it.setOnClickListener { checkCBs(button, checkBox) } }
}

private fun checkCBs(
    button: View,
    checkBox: Array<out CheckBox>
) {
    var buttonEnabled = true
    checkBox.forEach { if (!it.isChecked) buttonEnabled = false }
    button.isEnabled = buttonEnabled
}

fun checkButton(
    button: Button,
    vararg checkBox: CheckBox
) {
    checkBox.forEach { _ -> checkCBs(button, checkBox) }
}

fun Fragment.checkPermission(permission: String) = checkPermission(requireActivity(), permission)

fun Fragment.showCustomDialog(
    @StringRes title: Int? = null,
    @StringRes message: Int? = null,
    positive: () -> Unit = {},
    negative: () -> Unit = {},
    @StringRes positiveButton: Int? = android.R.string.ok,
    @StringRes negativeButton: Int? = null,
    @ColorRes positiveColor: Int? = R.color.primary,
    @ColorRes negativeColor: Int? = R.color.secondary
) {
    val dialogListener = DialogInterface.OnClickListener { _, which ->
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> {
                positive.invoke()
            }

            DialogInterface.BUTTON_NEGATIVE -> {
                negative.invoke()
            }
        }
    }

    val dialogBuilder = AlertDialog.Builder(activity!!)
    title?.let { dialogBuilder.setTitle(getString(it)) }
    message?.let { dialogBuilder.setMessage(getString(it)) }
    positiveButton?.let { dialogBuilder.setPositiveButton(getString(it), dialogListener) }
    negativeButton?.let { dialogBuilder.setNegativeButton(getString(it), dialogListener) }
    val dialog = dialogBuilder.create()

    dialog.setOnShowListener {
        negativeColor?.let {
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(ContextCompat.getColor(activity!!, it))
        }
        positiveColor?.let {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(ContextCompat.getColor(activity!!, it))
        }
    }

    dialog.show()
}