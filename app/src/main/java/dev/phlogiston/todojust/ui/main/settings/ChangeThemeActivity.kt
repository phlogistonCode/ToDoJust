package dev.phlogiston.todojust.ui.main.settings

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.phlogiston.todojust.R
import dev.phlogiston.todojust.core.extensions.postDelayed
import dev.phlogiston.todojust.core.prefs.AppStore
import dev.phlogiston.todojust.databinding.ActivityChangeThemeBinding
import dev.phlogiston.todojust.di.module.PersistanceModule
import java.io.FileInputStream


class ChangeThemeActivity: AppCompatActivity() {

    companion object {
        const val ARGUMENT_BITMAP = "ARGUMENT_BITMAP"
        const val ARGUMENT_X = "ARGUMENT_X"
        const val ARGUMENT_Y = "ARGUMENT_Y"

        fun newIntent(context: Context, filename: String, x: Int, y: Int): Intent =
            Intent(context, ChangeThemeActivity::class.java).apply {
                putExtra(ARGUMENT_BITMAP, filename)
                putExtra(ARGUMENT_X, x)
                putExtra(ARGUMENT_Y, y)
            }
    }

    val x by lazy { intent.extras?.getInt(ARGUMENT_X) ?: (binding.mask.right / 2) }
    val y by lazy { intent.extras?.getInt(ARGUMENT_Y) ?: (binding.mask.bottom / 2) }

    private val binding by viewBinding(ActivityChangeThemeBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        val appStore = AppStore(this.getSharedPreferences(PersistanceModule.PREFS_NAME, MODE_PRIVATE))
        val theme = appStore.getTheme()
        AppCompatDelegate.setDefaultNightMode(theme.nightMode)
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.do_not_move, R.anim.do_not_move)
        setTheme(theme.style)
        setContentView(R.layout.activity_change_theme)

        appStore.putThemeChanged(true)

        window.statusBarColor = ContextCompat.getColor(this, R.color.background)

        val filename = intent.extras?.getString(ARGUMENT_BITMAP)
        var bmp: Bitmap? = null
        try {
            val inputStream: FileInputStream = openFileInput(filename)
            bmp = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        bmp?.let {
            binding.mask.background = BitmapDrawable(resources, it)
            closingCircularActivity()
        } ?: finish()
    }

    private fun closingCircularActivity() {
        with(binding) {
            val viewTreeObserver: ViewTreeObserver = mask.viewTreeObserver

            if (viewTreeObserver.isAlive) {
                viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        startAnimClosing()
                        mask.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    }
                })
            }
        }
    }

    private fun startAnimClosing() {
        with(binding) {
            val finalRadius: Float = mask.width.coerceAtLeast(mask.height).toFloat()
            val circularReveal =
                ViewAnimationUtils.createCircularReveal(
                    mask,
                    x,
                    y,
                    finalRadius,
                    0f
                )
            circularReveal.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {}
                override fun onAnimationEnd(animator: Animator) {
                    finish()
                }

                override fun onAnimationCancel(animator: Animator) {}
                override fun onAnimationRepeat(animator: Animator) {}
            })
            circularReveal.duration = 700
                circularReveal.start()
        }
    }

}