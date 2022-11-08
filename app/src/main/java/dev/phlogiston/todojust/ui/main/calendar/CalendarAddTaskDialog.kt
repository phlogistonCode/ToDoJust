package dev.phlogiston.todojust.ui.main.calendar

import by.kirich1409.viewbindingdelegate.viewBinding
import dev.phlogiston.todojust.R
import dev.phlogiston.todojust.core.base.BottomSheetFragment
import dev.phlogiston.todojust.core.extensions.activityViewModel
import dev.phlogiston.todojust.databinding.DialogCalendarAddTaskBinding
import dev.phlogiston.todojust.ui.main.MainViewModel

class CalendarAddTaskDialog : BottomSheetFragment(R.layout.dialog_calendar_add_task) {

    private val binding by viewBinding(DialogCalendarAddTaskBinding::bind)
    private val viewModel by lazy { activityViewModel<MainViewModel>(viewModelFactory) }

    override fun bind() {
    }

    override fun initViews() {
        with(binding) {
            dismiss.setOnClickListener { dialog?.dismiss() }
            addText.setOnClickListener {
                viewModel.addNote(editText.text)
            }
        }
    }
}