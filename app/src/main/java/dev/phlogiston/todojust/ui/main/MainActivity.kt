package dev.phlogiston.todojust.ui.main

import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.core.view.isVisible
import dev.phlogiston.todojust.R
import dev.phlogiston.todojust.core.base.BaseActivity
import dev.phlogiston.todojust.core.extensions.isNotNullAndBlank
import dev.phlogiston.todojust.core.extensions.setColor
import dev.phlogiston.todojust.core.extensions.viewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override val viewModel by lazy { viewModel<MainViewModel>(viewModelFactory) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initScreen()

        with(viewModel) {
        }
    }

    override fun setTitle(text: String?) {
        toolbarTitle.text = text
    }

    fun setSubtitle(text: String? = null, @ColorRes color: Int? = null) {
        toolbarSubtitle.text = text
        toolbarSubtitle.isVisible = text.isNotNullAndBlank()
        color?.let {
            toolbarSubtitle.setColor(it)
        }
    }

    private fun initScreen() {
        router.newRootScreen(Screens.tasks())
        initBottomMenu()
    }

    private fun initBottomMenu() {
        bottomMenu.setOnNavigationItemSelectedListener {
            if (bottomMenu.selectedItemId != it.itemId) {
                when (it.itemId) {
                    R.id.bottom_tasks -> {
                        router.replaceScreen(Screens.tasks()); true
                    }
                    R.id.bottom_calendar -> {
                        router.replaceScreen(Screens.calendar()); true
                    }
                    else -> true
                }
            } else true
        }
    }

    fun showToolbar() {
        appbar.isVisible = true
    }

    fun hideToolbar() {
        appbar.isVisible = false
    }

}
