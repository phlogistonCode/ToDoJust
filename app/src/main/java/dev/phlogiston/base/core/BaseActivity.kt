package dev.phlogiston.base.core

import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import dagger.android.support.DaggerAppCompatActivity
import dev.phlogiston.base.R
import dev.phlogiston.base.core.extensions.viewModel
import dev.phlogiston.base.core.mvvm.BaseViewModel
import dev.phlogiston.base.core.mvvm.ViewModelFactory
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

abstract class BaseActivity: DaggerAppCompatActivity() {

    @get:IdRes
    protected abstract val layoutRes: Int

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    open val viewModel by lazy { viewModel<BaseViewModel>(viewModelFactory) }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(layoutRes)
        if (fragment is OnBackPressed) {
            if ((fragment as? OnBackPressed)?.onBackPressed()!!) super.onBackPressed()
        } else super.onBackPressed()
    }

    fun initToolbar(
        @StringRes title: Int,
        colorRes: Int = R.color.colorPrimaryDark,
        isCloseIcon: Boolean = false,
        needBackButton: Boolean = true,
        navigate: (() -> Unit?)? = null
    ) {
        val toolbar = toolbar
        val color = ContextCompat.getColor(this, colorRes)
        if (needBackButton) {
            if (isCloseIcon) toolbar.setNavigationIcon(R.drawable.ic_close_back_24dp)
            else toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp)
            toolbar.setNavigationOnClickListener {
                navigate?.invoke() ?: run { onBackPressed() }
            }
        }
        toolbar.title = getString(title)
        toolbar.navigationIcon?.setTint(color)
        toolbar.setTitleTextColor(color)
    }

    fun fadeInProgress() {
        this.findViewById<ViewGroup>(R.id.spinner)?.isVisible = true
    }

    fun fadeOutProgress() {
        this.findViewById<ViewGroup>(R.id.spinner)?.isVisible = false
    }

    fun <L : LiveData<Boolean>> loading(
        liveData: L,
        body: (Boolean) -> Unit = {
            if (it) {
                fadeInProgress()
            } else {
                fadeOutProgress()
            }
        }
    ) = liveData.observe(this, Observer(body))

}