package dev.phlogiston.todojust.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistanceModule {
    companion object {
        const val PREFS_NAME = "JustPrefs"
    }

    @Provides
    @Singleton
    internal fun providesSP(context: Context): SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

}