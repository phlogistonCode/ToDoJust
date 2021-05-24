package dev.phlogiston.todojust.ui.main.home

import android.view.View
import dev.phlogiston.todojust.R
import dev.phlogiston.todojust.core.base.BaseFragment
import dev.phlogiston.todojust.core.extensions.activityViewModel
import dev.phlogiston.todojust.ui.main.MainViewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    override val viewModel by lazy { activityViewModel<MainViewModel>(viewModelFactory) }

    override fun bind() {
        with(viewModel) {

        }
    }

    override fun initViews(view: View) {
    }
}