package dev.phlogiston.todojust.ui.main.tasks

import android.view.View
import dev.phlogiston.todojust.R
import dev.phlogiston.todojust.core.base.BaseMainFragment
import dev.phlogiston.todojust.core.extensions.activityViewModel
import dev.phlogiston.todojust.core.extensions.observeNullable
import dev.phlogiston.todojust.ui.main.MainViewModel

class TasksFragment : BaseMainFragment(R.layout.fragment_home) {

    override val viewModel by lazy { activityViewModel<MainViewModel>(viewModelFactory) }

    override fun bind() {
//        with(viewModel) {
//            getNotes()
//            observeNullable(notes) {
//                Log.d("asd", it.toString())
//            }
//        }
    }

    override fun initViews(view: View) {
    }
}