package dev.phlogiston.todojust.ui.main

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.phlogiston.todojust.core.mvvm.BaseViewModel
import dev.phlogiston.todojust.data.settings.ThemeChanging
import dev.phlogiston.todojust.db.tasks.Task
import dev.phlogiston.todojust.repos.TasksRepository
import java.time.LocalDate
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val tasksRepository: TasksRepository
) : BaseViewModel() {

    val selectedDate: LiveData<LocalDate>
        get() = mSelectedDate
    private val mSelectedDate = MutableLiveData<LocalDate>()

    val tasks: LiveData<List<Task>?>
        get() = mTasks
    private val mTasks = MutableLiveData<List<Task>?>()

    val tasksByDate: LiveData<List<Task>?>
        get() = mTasksByDate
    private val mTasksByDate = MutableLiveData<List<Task>?>()

    val themeChanging: LiveData<ThemeChanging>
        get() = mThemeChanging
    private val mThemeChanging = MutableLiveData<ThemeChanging>()

    fun getTasks() {
        launch(tasksRepository, TasksRepository.Params()) {
            mTasks.value = it
        }
    }

    fun getTasksByDate(date: LocalDate) {
        launch(tasksRepository, TasksRepository.Params(date)) {
            mTasksByDate.value = it
        }
    }

    fun addNote(text: Editable?) {
        text?.let {
            launch {
                tasksRepository.insert(
                    Task(
                        text = text.toString(),
                        date = mSelectedDate.value ?: LocalDate.now()
                    )
                )
            }
        }
    }

    fun selectDate(date: LocalDate) {
        mSelectedDate.value = date
    }

    fun changeTheme(themeChanging: ThemeChanging) {
        mThemeChanging.value = themeChanging
    }

}