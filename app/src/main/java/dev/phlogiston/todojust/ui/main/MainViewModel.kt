package dev.phlogiston.todojust.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.phlogiston.todojust.core.mvvm.BaseViewModel
import dev.phlogiston.todojust.db.notes.Note
import dev.phlogiston.todojust.repos.NotesRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val notesRepository: NotesRepository
) : BaseViewModel() {

    val notes: LiveData<List<Note>?>
        get() = mNotes
    private val mNotes = MutableLiveData<List<Note>?>()

    fun getNotes() {
        launch(notesRepository) {
            mNotes.value = it
        }
    }

}