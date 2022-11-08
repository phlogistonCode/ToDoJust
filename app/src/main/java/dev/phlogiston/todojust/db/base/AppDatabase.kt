package dev.phlogiston.todojust.db.base

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.phlogiston.todojust.db.tasks.Task
import dev.phlogiston.todojust.db.tasks.TasksDao

@Database(
    entities = [
        Task::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tasks(): TasksDao
}