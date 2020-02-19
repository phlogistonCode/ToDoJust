package dev.phlogiston.todojust.main

import android.os.Bundle
import dev.phlogiston.todojust.R
import dev.phlogiston.todojust.core.BaseActivity
import dev.phlogiston.todojust.core.extensions.viewModel

class MainActivity : BaseActivity() {

    override val layoutRes: Int = R.id.container

    override val viewModel by lazy { viewModel<MainViewModel>(viewModelFactory) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        with(viewModel) {
        }
    }
}
