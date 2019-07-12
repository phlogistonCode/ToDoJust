package com.phlogiston.todojust.app

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ToDoJustApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}