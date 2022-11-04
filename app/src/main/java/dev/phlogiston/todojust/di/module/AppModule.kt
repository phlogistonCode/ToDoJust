package dev.phlogiston.todojust.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.phlogiston.todojust.ToDoJustApp
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun proveAppContext(toDoJustApp: ToDoJustApp): Context = toDoJustApp.applicationContext

}