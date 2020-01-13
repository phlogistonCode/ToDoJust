package dev.phlogiston.base

import android.content.res.Resources
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dev.phlogiston.base.di.DaggerAppComponent

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
        res = resources
    }

    companion object {
        lateinit var res: Resources
    }
}