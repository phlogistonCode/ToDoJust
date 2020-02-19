package dev.phlogiston.todojust.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.phlogiston.todojust.App
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun proveAppContext(app: App): Context = app.applicationContext

}