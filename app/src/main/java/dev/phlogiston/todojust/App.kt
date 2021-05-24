package dev.phlogiston.todojust

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dev.phlogiston.todojust.di.DaggerAppComponent

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
    }

}