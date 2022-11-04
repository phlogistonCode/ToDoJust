package dev.phlogiston.todojust.ui.main

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.ColorRes
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.phlogiston.todojust.R
import dev.phlogiston.todojust.core.base.BaseActivity
import dev.phlogiston.todojust.core.extensions.isNotNullAndBlank
import dev.phlogiston.todojust.core.extensions.observe
import dev.phlogiston.todojust.core.extensions.setColor
import dev.phlogiston.todojust.core.extensions.viewModel
import dev.phlogiston.todojust.data.settings.Theme
import dev.phlogiston.todojust.data.settings.ThemeChanging
import dev.phlogiston.todojust.databinding.ActivityMainBinding
import dev.phlogiston.todojust.ui.main.settings.ChangeThemeActivity
import timber.log.Timber
import java.io.FileOutputStream

class MainActivity : BaseActivity() {

    override val viewModel by lazy { viewModel<MainViewModel>(viewModelFactory) }

    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initScreen()

        with(viewModel) {
            observe(themeChanging) {
                setTheme(it, false)
            }
        }
    }

    override fun setTitle(text: String?) {
        binding.toolbarTitle.text = text
    }

    fun setSubtitle(text: String? = null, @ColorRes color: Int? = null) {
        with(binding.toolbarSubtitle) {
            this.text = text
            isVisible = text.isNotNullAndBlank()
            color?.let {
                setColor(it)
            }
        }
    }

    private fun initScreen() {
        if (!appStore.isThemeChanged()) {
            router.newRootScreen(Screens.tasks())
        }
        initBottomMenu()
    }

    private fun initBottomMenu() {
        with(binding.bottomMenu) {
            setOnItemSelectedListener {
                if (selectedItemId != it.itemId) {
                    when (it.itemId) {
                        R.id.bottom_tasks -> {
                            router.replaceScreen(Screens.tasks()); true
                        }
                        R.id.bottom_calendar -> {
                            router.replaceScreen(Screens.calendar()); true
                        }
                        R.id.bottom_settings -> {
                            router.replaceScreen(Screens.settings()); true
                        }
                        else -> true
                    }
                } else true
            }
        }
    }

    fun showToolbar() {
        binding.appbar.isVisible = true
    }

    fun hideToolbar() {
        binding.appbar.isVisible = false
    }

    private fun setTheme(themeChanging: ThemeChanging, animate: Boolean = true) {
        if (appStore.getTheme() == themeChanging.theme) {
            return
        }

        appStore.putTheme(themeChanging.theme)

        if (!animate) {
            recreateWithoutAnim()
            return
        }

        with(binding) {
            val w = parent.measuredWidth
            val h = parent.measuredHeight

            try {
                val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(bitmap)
                parent.draw(canvas)


                val filename = "bitmap.png"
                val stream: FileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)

                stream.close()
                bitmap.recycle()

                startActivity(
                    ChangeThemeActivity.newIntent(
                        this@MainActivity,
                        filename,
                        themeChanging.centerX,
                        themeChanging.centerY
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
                recreateWithoutAnim()
            }
        }
    }

    fun recreateWithoutAnim() {
        recreate()
        appStore.putThemeChanged(true)
    }

}
