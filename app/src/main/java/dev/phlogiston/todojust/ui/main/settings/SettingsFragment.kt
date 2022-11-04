package dev.phlogiston.todojust.ui.main.settings

import android.view.View
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.color.DynamicColors
import dev.phlogiston.todojust.R
import dev.phlogiston.todojust.core.base.BaseMainFragment
import dev.phlogiston.todojust.core.extensions.activityViewModel
import dev.phlogiston.todojust.core.extensions.getCenterAbsoluteX
import dev.phlogiston.todojust.core.extensions.getCenterAbsoluteY
import dev.phlogiston.todojust.core.prefs.AppStore
import dev.phlogiston.todojust.data.settings.Theme
import dev.phlogiston.todojust.data.settings.ThemeChanging
import dev.phlogiston.todojust.databinding.FragmentSettingsBinding
import dev.phlogiston.todojust.ui.main.MainViewModel
import javax.inject.Inject

class SettingsFragment : BaseMainFragment(R.layout.fragment_settings) {

    @Inject
    lateinit var appStore: AppStore

    private val binding by viewBinding(FragmentSettingsBinding::bind)
    override val viewModel by lazy { activityViewModel<MainViewModel>(viewModelFactory) }

    override fun bind() {
    }

    override fun initViews(view: View) {
        with(binding) {
            dynamicSwitch.isChecked = appStore.isDynamicTheme()
            dynamicSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
                appStore.setDynamicTheme(isChecked)
                if (isChecked) DynamicColors.applyToActivityIfAvailable(mainActivity)
                mainActivity.recreateWithoutAnim()
            }
            val themeAdapter = ThemesAdapter(appStore.getTheme()) {
                viewModel.changeTheme(it)
            }
            themeAdapter.submitList(Theme.values().toList())
            with(themes) {
                adapter = themeAdapter
            }
        }
    }
}