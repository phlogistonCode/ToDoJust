package dev.phlogiston.todojust.ui.main

import android.os.Bundle
import dev.phlogiston.todojust.R
import dev.phlogiston.todojust.core.base.BaseActivity
import dev.phlogiston.todojust.core.extensions.viewModel

class MainActivity : BaseActivity() {

    override val viewModel by lazy { viewModel<MainViewModel>(viewModelFactory) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        initScreen()

        with(viewModel) {
        }
    }

    private fun initScreen() {
        router.newRootScreen(Screens.home())
    }

}
