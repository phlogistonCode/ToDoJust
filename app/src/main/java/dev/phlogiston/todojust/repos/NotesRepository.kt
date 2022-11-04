package dev.phlogiston.todojust.repos

import dev.phlogiston.todojust.core.data.Repository
import dev.phlogiston.todojust.core.data.Resource
import dev.phlogiston.todojust.db.notes.Note
import dev.phlogiston.todojust.db.notes.NotesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepository @Inject constructor(
    private val notesDao: NotesDao
) : Repository<List<Note>?, Any>() {

    override suspend fun run(params: Any): Flow<Resource<List<Note>>> =
        executeDb {
            notesDao.get()
        }

    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }
}