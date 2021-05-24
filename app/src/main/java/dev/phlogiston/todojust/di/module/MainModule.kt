package dev.phlogiston.todojust.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.phlogiston.todojust.ui.main.home.HomeFragment

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    abstract fun mainFragment(): HomeFragment

}