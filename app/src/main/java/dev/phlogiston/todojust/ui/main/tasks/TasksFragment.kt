package dev.phlogiston.todojust.ui.main.tasks

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.phlogiston.todojust.R
import dev.phlogiston.todojust.core.base.BaseMainFragment
import dev.phlogiston.todojust.core.extensions.activityViewModel
import dev.phlogiston.todojust.core.extensions.clearText
import dev.phlogiston.todojust.core.extensions.enableBtnIfNotEmptyEditText
import dev.phlogiston.todojust.core.extensions.observeNullable
import dev.phlogiston.todojust.databinding.FragmentTasksBinding
import dev.phlogiston.todojust.ui.main.MainViewModel
import timber.log.Timber

class TasksFragment : BaseMainFragment(R.layout.fragment_tasks) {

    private val binding by viewBinding(FragmentTasksBinding::bind)
    override val viewModel by lazy { activityViewModel<MainViewModel>(viewModelFactory) }

    private val tasksAdapter = TasksAdapter()

    override fun bind() {
        with(viewModel) {
            getNotes()
            observeNullable(notes) {
                tasksAdapter.submitList(it)
            }
        }
    }

    override fun initViews(view: View) {
        with(binding) {
            with(recycler) {
                adapter = tasksAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
            enableBtnIfNotEmptyEditText(addText, editText)
            addText.setOnClickListener {
                viewModel.addNote(editText.text)
                editText.clearText()
            }
        }
    }
}