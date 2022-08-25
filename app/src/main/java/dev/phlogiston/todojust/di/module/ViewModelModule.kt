package dev.phlogiston.todojust.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.phlogiston.todojust.core.mvvm.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.phlogiston.todojust.core.mvvm.ViewModelFactory
import dev.phlogiston.todojust.ui.main.MainViewModel

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}