package dev.phlogiston.todojust.di.module

import android.content.Context
import androidx.room.Room
import dev.phlogiston.todojust.db.base.AppDatabase
import dagger.Module
import dagger.Provides
import dev.phlogiston.todojust.db.notes.NotesDao
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
            .addMigrations(
            )
            .build()

    @Provides
    @Singleton
    fun provideNotesDao(db: AppDatabase): NotesDao = db.notes()

}