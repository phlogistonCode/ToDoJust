package dev.phlogiston.todojust.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.phlogiston.todojust.core.mvvm.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
<<<<<<< HEAD:app/src/main/java/dev/phlogiston/todojust/di/module/ViewModelModule.kt
import dev.phlogiston.todojust.core.mvvm.ViewModelFactory
import dev.phlogiston.todojust.main.MainViewModel
=======
import dev.phlogiston.base.core.mvvm.ViewModelFactory
import dev.phlogiston.todojust.ui.main.MainViewModel
>>>>>>> base/master:app/src/main/java/dev/phlogiston/base/di/module/ViewModelModule.kt

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}