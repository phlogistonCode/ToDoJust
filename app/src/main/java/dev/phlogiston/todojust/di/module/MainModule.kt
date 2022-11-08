package dev.phlogiston.todojust.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.phlogiston.todojust.ui.main.calendar.CalendarAddTaskDialog
import dev.phlogiston.todojust.ui.main.calendar.CalendarFragment
import dev.phlogiston.todojust.ui.main.settings.SettingsFragment
import dev.phlogiston.todojust.ui.main.tasks.TasksFragment

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    abstract fun tasksFragment(): TasksFragment

    @ContributesAndroidInjector
    abstract fun calendarFragment(): CalendarFragment

    @ContributesAndroidInjector
    abstract fun settingsFragment(): SettingsFragment

    @ContributesAndroidInjector
    abstract fun calendarAddTaskDialog(): CalendarAddTaskDialog

}