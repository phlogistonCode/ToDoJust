package com.phlogiston.todojust.di.modules

import com.phlogiston.todojust.di.scope.PerActivity
import com.phlogiston.todojust.main.MainActivity
import com.phlogiston.todojust.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideMainActivity() : MainActivity
}