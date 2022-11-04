package dev.phlogiston.todojust.core.tools

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import dev.phlogiston.todojust.R

open class HideableSupportAppNavigator(activity: FragmentActivity, containerId: Int) :
        AppNavigator(activity, containerId) {

    override fun applyCommand(command: Command) {
        val view = activity.currentFocus
        view?.let {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
        super.applyCommand(command)
    }

    override fun setupFragmentTransaction(
        screen: FragmentScreen,
        fragmentTransaction: FragmentTransaction,
        currentFragment: Fragment?,
        nextFragment: Fragment
    ) {
        super.setupFragmentTransaction(screen, fragmentTransaction, currentFragment, nextFragment)
        fragmentTransaction.setCustomAnimations(
            R.anim.slide_in_left,
            R.anim.slide_out_right,
            R.anim.slide_out_left,
            R.anim.slide_in_right
        )
    }
}