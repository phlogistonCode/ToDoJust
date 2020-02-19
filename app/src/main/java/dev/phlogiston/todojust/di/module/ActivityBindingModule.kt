package dev.phlogiston.todojust.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.phlogiston.todojust.main.MainActivity

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideMainActivity() : MainActivity
}