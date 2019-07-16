package com.phlogiston.todojust.di

import android.app.Application
import com.phlogiston.todojust.app.ToDoJustApp
import com.phlogiston.todojust.di.modules.ActivityBindingModule
import com.phlogiston.todojust.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBindingModule::class])
interface AppComponent : AndroidInjector<ToDoJustApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}