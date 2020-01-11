package dev.phlogiston.base.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.phlogiston.base.main.MainActivity

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideMainActivity() : MainActivity
}