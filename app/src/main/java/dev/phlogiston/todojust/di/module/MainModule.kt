package dev.phlogiston.todojust.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.phlogiston.todojust.ui.main.MainFragment

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    abstract fun mainFragment(): MainFragment

}