package dev.phlogiston.todojust.repos

import dev.phlogiston.todojust.core.data.Repository
import dev.phlogiston.todojust.core.data.Resource
import dev.phlogiston.todojust.db.tasks.Task
import dev.phlogiston.todojust.db.tasks.TasksDao
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class TasksRepository @Inject constructor(
    private val tasksDao: TasksDao
) : Repository<List<Task>?, TasksRepository.Params>() {

    override suspend fun run(params: Params): Flow<Resource<List<Task>>> =
        executeDb {
            params.date?.let {
                tasksDao.get(it)
            } ?: tasksDao.get()
        }

    suspend fun insert(task: Task) {
        tasksDao.insert(task)
    }

    class Params(val date: LocalDate? = null)
}