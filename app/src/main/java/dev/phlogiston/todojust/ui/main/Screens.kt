package dev.phlogiston.todojust.ui.main

import android.content.Intent
import android.net.Uri
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import dev.phlogiston.todojust.ui.main.calendar.CalendarFragment
import dev.phlogiston.todojust.ui.main.settings.SettingsFragment
import dev.phlogiston.todojust.ui.main.tasks.TasksFragment

object Screens {

    fun main() = ActivityScreen { Intent(it, MainActivity::class.java) }

    fun tasks() = FragmentScreen { TasksFragment() }

    fun calendar() = FragmentScreen { CalendarFragment() }

    fun settings() = FragmentScreen { SettingsFragment() }

    fun shareLink(url: String, title: String) = ActivityScreen {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        shareIntent.putExtra(Intent.EXTRA_TEXT, url)
        Intent.createChooser(
            shareIntent,
            title
        )
    }

    fun actionUrl(url: String) = ActivityScreen { Intent(Intent.ACTION_VIEW, Uri.parse(url)) }

    fun share(uri: String?) = ActivityScreen {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, uri)
        }
        Intent.createChooser(
            shareIntent,
            "Выберите приложение"
        )
    }
}
