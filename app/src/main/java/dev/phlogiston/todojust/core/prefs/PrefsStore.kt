package dev.phlogiston.todojust.core.prefs

import android.content.SharedPreferences
import androidx.core.content.edit

abstract class PrefsStore(private val sp: SharedPreferences) {

    fun put(key: String, value: String) {
        sp.edit(true) {
            putString(key, value)
        }
    }

    fun put(key: String, value: Boolean) {
        sp.edit(true) {
            putBoolean(key, value)
        }
    }

    fun <T: Enum<T>> put(key: String, value: T) {
        sp.edit(true) {
            putInt(key, value.ordinal)
        }
    }

    fun getString(key: String): String? {
        if (sp.contains(key)) {
            return sp.getString(key, "")
        }
        return null
    }

    fun getInt(key: String): Int? {
        if (sp.contains(key)) {
            return sp.getInt(key, -1)
        }
        return null
    }

    fun getBoolean(key: String): Boolean? {
        if (sp.contains(key)) {
            return sp.getBoolean(key, false)
        }
        return null
    }

    fun remove(key: String) {
        sp.edit { remove(key) }
    }

    fun clear(keys: List<String>? = null) {
        sp.edit { keys?.forEach { remove(it) } ?: clear() }
    }

}