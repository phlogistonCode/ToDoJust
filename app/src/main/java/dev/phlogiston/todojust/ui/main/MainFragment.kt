<<<<<<< HEAD:app/src/main/java/dev/phlogiston/todojust/main/MainFragment.kt
package dev.phlogiston.todojust.main
=======
package dev.phlogiston.base.ui.main
>>>>>>> base/master:app/src/main/java/dev/phlogiston/todojust/ui/main/MainFragment.kt

import android.view.View
import dev.phlogiston.todojust.R
import dev.phlogiston.todojust.core.BaseFragment
import dev.phlogiston.todojust.core.extensions.activityViewModel

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