package com.phlogiston.todojust.main

import com.phlogiston.todojust.di.scope.PerActivity
import com.phlogiston.todojust.di.scope.PerFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

    //TODO: complete
//    @PerFragment
//    @ContributesAndroidInjector
//    internal abstract fun statisticsFragment(): StatisticsFragment

    @PerActivity
    @Binds
    internal abstract fun mainPresenter(presenter: MainPresenter): MainContract.Presenter
}