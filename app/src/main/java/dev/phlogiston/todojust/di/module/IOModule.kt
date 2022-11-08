package dev.phlogiston.todojust.di.module

import android.content.Context
import androidx.room.Room
import dev.phlogiston.todojust.db.base.AppDatabase
import dagger.Module
import dagger.Provides
import dev.phlogiston.todojust.db.tasks.TasksDao
import javax.inject.Singleton

@Module
class IOModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java, "just_database"
        )
            .fallbackToDestructiveMigration()
            .addMigrations(
            )
            .build()

    @Provides
    @Singleton
    fun provideNotesDao(db: AppDatabase): TasksDao = db.tasks()

}