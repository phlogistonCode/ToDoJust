package dev.phlogiston.todojust.di.module

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {
    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }

    @Provides
    @Singleton
    internal fun provideCiceroneRouter(): Router = cicerone.router

    @Provides
    @Singleton
    internal fun provideNavigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()
}