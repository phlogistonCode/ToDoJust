package dev.phlogiston.todojust.core.base

import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import dev.phlogiston.todojust.ui.main.MainActivity

abstract class BaseMainFragment(@LayoutRes layoutRes: Int): BaseFragment(layoutRes) {

    val mainActivity by lazy { activity as MainActivity }

    fun setSubtitle(text: String? = null, @ColorRes color: Int? = null) {
        mainActivity.setSubtitle(text, color)
    }

}