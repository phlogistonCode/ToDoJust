package dev.phlogiston.base.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.phlogiston.base.main.MainFragment

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    abstract fun mainFragment(): MainFragment

}