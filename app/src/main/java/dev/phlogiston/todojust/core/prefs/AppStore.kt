package dev.phlogiston.todojust.core.prefs

import android.content.Context
import android.content.SharedPreferences
import dev.phlogiston.todojust.data.settings.Theme
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppStore @Inject constructor(sp: SharedPreferences): PrefsStore(sp) {

    companion object {
        private const val THEME = "theme"
        private const val THEME_CHANGED = "theme_changed"
        private const val DYNAMIC_THEME = "dynamic_theme"
    }

    fun putTheme(theme: Theme) {
        put(THEME, theme)
    }

    fun getTheme(): Theme {
        return Theme.from(getInt(THEME))
    }

    fun putThemeChanged(isChanged: Boolean) {
        put(THEME_CHANGED, isChanged)
    }

    fun isThemeChanged(): Boolean {
        val isChanged = getBoolean(THEME_CHANGED) ?: false
        putThemeChanged(false)
        return isChanged
    }

    fun setDynamicTheme(isDynamic: Boolean) {
        put(DYNAMIC_THEME, isDynamic)
    }

    fun isDynamicTheme(): Boolean {
        return getBoolean(DYNAMIC_THEME) ?: false
    }

}