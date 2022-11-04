package dev.phlogiston.todojust.data.settings

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatDelegate
import dev.phlogiston.todojust.R
import dev.phlogiston.todojust.ToDoJustApp
import dev.phlogiston.todojust.core.extensions.isNightMode

enum class Theme(
    @DrawableRes val icon: Int,
    @StyleRes val style: Int,
    val nightMode: Int = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    ) {
    Light(
        R.drawable.ic_theme_light,
        R.style.Base_Theme_ToDoJust,
        AppCompatDelegate.MODE_NIGHT_NO
    ),
    Dark(
        R.drawable.ic_theme_dark,
        R.style.Base_Theme_Night_ToDoJust,
        AppCompatDelegate.MODE_NIGHT_YES
    );

    companion object {

        fun from(int: Int?): Theme {
            return when (int) {
                0 -> Light
                1 -> Dark
                else -> if (ToDoJustApp.appContext?.isNightMode() == true) Dark else Light
            }
        }

    }
}
