package com.phlogiston.todojust.main

import com.phlogiston.todojust.di.scope.PerActivity
import com.phlogiston.todojust.di.scope.PerFragment
import com.phlogiston.todojust.notes.NotesFragment
import com.phlogiston.todojust.notes.NotesModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [NotesModule::class])
    abstract fun notesFragment(): NotesFragment

    @PerActivity
    @Binds
    abstract fun mainPresenter(presenter: MainPresenter): MainContract.Presenter
}