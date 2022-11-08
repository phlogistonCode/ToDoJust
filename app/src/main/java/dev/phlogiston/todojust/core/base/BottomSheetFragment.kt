package dev.phlogiston.todojust.core.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dev.phlogiston.todojust.R
import dev.phlogiston.todojust.core.mvvm.ViewModelFactory
import javax.inject.Inject

abstract class BottomSheetFragment(@LayoutRes private val layoutRes: Int) : BottomSheetDialogFragment(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun getTheme(): Int = R.style.BottomSheetDialog

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        BottomSheetDialog(requireContext(), theme).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.skipCollapsed = true
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutRes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bind()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    abstract fun bind()

    abstract fun initViews()

    fun showSystemMessage(message: String) {
        context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }

    fun showSystemMessage(@StringRes res: Int) {
        showSystemMessage(getString(res))
    }

    fun setBehaviorState(state: Int) {
        (dialog as BottomSheetDialog).behavior.state = state
    }
}