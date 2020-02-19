package dev.phlogiston.todojust.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
<<<<<<< HEAD:app/src/main/java/dev/phlogiston/todojust/di/module/MainModule.kt
import dev.phlogiston.todojust.main.MainFragment
=======
import dev.phlogiston.todojust.ui.main.MainFragment
>>>>>>> base/master:app/src/main/java/dev/phlogiston/base/di/module/MainModule.kt

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    abstract fun mainFragment(): MainFragment

}