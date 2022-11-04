package dev.phlogiston.todojust.core.base

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.google.android.material.color.DynamicColors
import dagger.android.support.DaggerAppCompatActivity
import dev.phlogiston.todojust.R
import dev.phlogiston.todojust.core.extensions.viewModel
import dev.phlogiston.todojust.core.mvvm.BaseViewModel
import dev.phlogiston.todojust.core.mvvm.ViewModelFactory
import dev.phlogiston.todojust.core.prefs.AppStore
import dev.phlogiston.todojust.core.tools.HideableSupportAppNavigator
import dev.phlogiston.todojust.di.module.PersistanceModule
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

abstract class BaseActivity: DaggerAppCompatActivity() {

    lateinit var appStore: AppStore

    private val navigator: Navigator by lazy {
        HideableSupportAppNavigator(this, R.id.container)
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    open val viewModel by lazy { viewModel<BaseViewModel>(viewModelFactory) }

    abstract fun setTitle(text: String?)

    override fun onCreate(savedInstanceState: Bundle?) {
        appStore = AppStore(this.getSharedPreferences(PersistanceModule.PREFS_NAME, Context.MODE_PRIVATE))
        val theme = appStore.getTheme()
        AppCompatDelegate.setDefaultNightMode(theme.nightMode)
        super.onCreate(savedInstanceState)
        setTheme(theme.style)
        if (appStore.isDynamicTheme()) DynamicColors.applyToActivityIfAvailable(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onSupportNavigateUp(): Boolean {
        router.exit()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        (fragment as? OnBackPressed)?.onBackPressed()?.not()?.let {
            if (it) {
                router.exit()
            }
        } ?: router.exit()
    }

    fun showSystemMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showSystemMessage(@StringRes res: Int) {
        showSystemMessage(getString(res))
    }

    fun initToolbar(
        @StringRes title: Int,
        colorRes: Int = R.color.background,
        isCloseIcon: Boolean = false,
        needBackButton: Boolean = true,
        navigate: (() -> Unit?)? = null
    ) {
        val toolbar = toolbar
        val color = ContextCompat.getColor(this, colorRes)
        if (needBackButton) {
            if (isCloseIcon) toolbar.setNavigationIcon(R.drawable.ic_close)
            else toolbar.setNavigationIcon(R.drawable.ic_back)
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