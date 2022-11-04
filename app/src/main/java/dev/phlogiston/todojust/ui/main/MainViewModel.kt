package dev.phlogiston.todojust.ui.main

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.phlogiston.todojust.core.mvvm.BaseViewModel
import dev.phlogiston.todojust.data.settings.ThemeChanging
import dev.phlogiston.todojust.db.notes.Note
import dev.phlogiston.todojust.db.notes.NotesDao
import dev.phlogiston.todojust.repos.NotesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val notesRepository: NotesRepository
) : BaseViewModel() {

    val notes: LiveData<List<Note>?>
        get() = mNotes
    private val mNotes = MutableLiveData<List<Note>?>()

    val themeChanging: LiveData<ThemeChanging>
        get() = mThemeChanging
    private val mThemeChanging = MutableLiveData<ThemeChanging>()

    fun getNotes() {
        launch(notesRepository) {
            mNotes.value = it
        }
    }

    fun addNote(text: Editable?) {
        text?.let {
            launch {
                notesRepository.insert(
                    Note(
                        text = text.toString()
                    )
                )
            }
        }
    }

    fun changeTheme(themeChanging: ThemeChanging) {
        mThemeChanging.value = themeChanging
    }

}