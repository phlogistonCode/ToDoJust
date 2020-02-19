package dev.phlogiston.base.ui.main

import android.view.View
import dev.phlogiston.base.R
import dev.phlogiston.base.core.BaseFragment
import dev.phlogiston.base.core.extensions.activityViewModel

class MainFragment : BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_main

    override val viewModel by lazy { activityViewModel<MainViewModel>(viewModelFactory) }

    override fun bind() {
        with(viewModel) {

        }
    }

    override fun initViews(view: View) {
    }
}