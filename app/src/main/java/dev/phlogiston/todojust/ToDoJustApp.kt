package dev.phlogiston.todojust

import android.content.Context
import com.google.android.material.color.DynamicColors
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dev.phlogiston.todojust.core.prefs.AppStore
import dev.phlogiston.todojust.di.DaggerAppComponent
import javax.inject.Inject

class ToDoJustApp : DaggerApplication() {

    @Inject
    lateinit var appStore: AppStore

    companion object {
        var appContext: Context? = null
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
        if (appStore.isDynamicTheme()) DynamicColors.applyToActivitiesIfAvailable(this)
        appContext = applicationContext
    }

}