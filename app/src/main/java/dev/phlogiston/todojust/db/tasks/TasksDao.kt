package dev.phlogiston.todojust.db.tasks

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface TasksDao {

    @Query("SELECT * FROM Task")
    fun get(): Flow<List<Task>>

    @Query("SELECT * FROM Task where id=:noteId")
    fun get(noteId: Int): Flow<Task>

    @Query("SELECT * FROM Task where date=:date")
    fun get(date: LocalDate): Flow<List<Task>>

    @Query("SELECT * FROM Task where id=:noteId LIMIT 1")
    fun getSync(noteId: Int): Task

    @Query("DELETE FROM Task")
    fun delete()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)
}