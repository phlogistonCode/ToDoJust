package dev.phlogiston.todojust.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dev.phlogiston.todojust.ToDoJustApp
import dev.phlogiston.todojust.di.module.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBindingModule::class,
        IOModule::class,
        PersistanceModule::class,
        NavigationModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<ToDoJustApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(toDoJustApp: ToDoJustApp): Builder

        fun build(): AppComponent
    }
}