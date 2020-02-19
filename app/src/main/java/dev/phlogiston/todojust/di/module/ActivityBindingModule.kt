package dev.phlogiston.todojust.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
<<<<<<< HEAD:app/src/main/java/dev/phlogiston/todojust/di/module/ActivityBindingModule.kt
import dev.phlogiston.todojust.main.MainActivity
=======
import dev.phlogiston.todojust.ui.main.MainActivity
>>>>>>> base/master:app/src/main/java/dev/phlogiston/base/di/module/ActivityBindingModule.kt

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideMainActivity() : MainActivity
}