package dev.phlogiston.todojust.db.base

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.phlogiston.todojust.db.notes.Note
import dev.phlogiston.todojust.db.notes.NotesDao

@Database(
    entities = [
        Note::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notes(): NotesDao
}