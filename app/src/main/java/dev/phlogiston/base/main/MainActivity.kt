package dev.phlogiston.base.main

import android.os.Bundle
import dev.phlogiston.base.R
import dev.phlogiston.base.core.BaseActivity
import dev.phlogiston.base.core.extensions.viewModel

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
