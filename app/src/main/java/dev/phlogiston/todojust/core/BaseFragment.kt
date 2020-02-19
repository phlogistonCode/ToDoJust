package dev.phlogiston.todojust.core

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import dagger.android.support.DaggerFragment
import dev.phlogiston.todojust.R
import dev.phlogiston.todojust.core.extensions.viewModel
import dev.phlogiston.todojust.core.mvvm.BaseViewModel
import dev.phlogiston.todojust.core.mvvm.ViewModelFactory
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

abstract class BaseFragment: DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @get:LayoutRes
    protected abstract val layoutRes: Int

    open val viewModel by lazy { viewModel<BaseViewModel>(viewModelFactory) }

    open val title: String? = null
    open val subTitle: String? = null
    @ColorRes
    open val statusBarColor: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = activity!!.layoutInflater.inflate(layoutRes, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bind()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    override fun onResume() {
        super.onResume()
        if (statusBarColor != null) {
            context?.let {
                activity?.window?.statusBarColor =
                    ContextCompat.getColor(it, statusBarColor!!)
            }
        } else {
            context?.let {
                activity?.window?.statusBarColor =
                    ContextCompat.getColor(it, R.color.colorPrimaryDark)
            }
        }
    }

    abstract fun bind()
    abstract fun initViews(view: View)

    fun initToolbar(
        @StringRes title: Int,
        colorRes: Int = R.color.colorPrimaryDark,
        isCloseIcon: Boolean = false,
        needBackButton: Boolean = true,
        navigate: (() -> Unit?)? = null
    ) {
        if (needBackButton) {
            if (isCloseIcon) toolbar.setNavigationIcon(R.drawable.ic_close_back_24dp)
            else toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp)
            toolbar.setNavigationOnClickListener {
                navigate?.invoke() ?: run { activity?.onBackPressed() }
            }
        }
        toolbar.title = getString(title)
        val color = ContextCompat.getColor(activity!!, colorRes)
        toolbar.navigationIcon?.let { DrawableCompat.setTint(it.mutate(), color) }
        toolbar.setTitleTextColor(color)
    }

    fun hideKeyboard() {
        activity?.let { activity ->
            val view = activity.currentFocus
            view?.let {
                val imm =
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }

    fun showKeyboard(editText: EditText) {
        activity?.let { activity ->
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun showKeyBoardAndFocus(editText: EditText) {
        editText.requestFocus()
        activity?.let { activity ->
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun fadeInProgress() {
        activity?.findViewById<ViewGroup>(R.id.spinner)?.isVisible = true
    }

    fun fadeOutProgress() {
        activity?.findViewById<ViewGroup>(R.id.spinner)?.isVisible = false
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