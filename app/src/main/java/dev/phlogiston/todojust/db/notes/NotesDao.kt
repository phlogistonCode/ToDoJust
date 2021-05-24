package dev.phlogiston.todojust.db.notes

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM Note where id=:noteId")
    fun get(noteId: Int): Flow<Note>

    @Query("SELECT * FROM Note where id=:noteId LIMIT 1")
    fun getSync(noteId: Int): Note

    @Query("DELETE FROM Note")
    fun delete()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)
}