package dev.phlogiston.todojust.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.phlogiston.todojust.ui.main.calendar.CalendarFragment
import dev.phlogiston.todojust.ui.main.tasks.TasksFragment

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    abstract fun tasksFragment(): TasksFragment

    @ContributesAndroidInjector
    abstract fun calendarFragment(): CalendarFragment

}