package dev.phlogiston.base.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.phlogiston.base.App
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun proveAppContext(app: App): Context = app.applicationContext

}