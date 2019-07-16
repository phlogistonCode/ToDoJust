package com.phlogiston.todojust.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.phlogiston.todojust.Planning.PlanningFragment
import com.phlogiston.todojust.R
import com.phlogiston.todojust.R.id.*
import com.phlogiston.todojust.Settings.SettingsFragment
import com.phlogiston.todojust.notes.NotesFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var fragmentNotes: NotesFragment

//    private val fragmentNotes = NotesFragment()
    private val fragmentPlanning = PlanningFragment()
    private val fragmentSettings = SettingsFragment()

    //private var activeFragment: Fragment = fragmentNotes

//    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//        when (item.itemId) {
//            navigation_notes -> {
//                supportFragmentManager
//                    .beginTransaction()
//                    .hide(activeFragment)
//                    .show(fragmentNotes)
//                    .commit()
//
//                activeFragment = fragmentNotes
//                return@OnNavigationItemSelectedListener true
//            }
//            navigation_planning -> {
//                supportFragmentManager
//                    .beginTransaction()
//                    .hide(activeFragment)
//                    .show(fragmentPlanning)
//                    .commit()
//
//                activeFragment = fragmentPlanning
//                return@OnNavigationItemSelectedListener true
//            }
//            navigation_settings -> {
//                supportFragmentManager
//                    .beginTransaction()
//                    .hide(activeFragment)
//                    .show(fragmentSettings)
//                    .commit()
//
//                activeFragment = fragmentSettings
//                return@OnNavigationItemSelectedListener true
//            }
//        }
//        false
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
//        addFragment(fragmentNotes)
//        addFragment(fragmentPlanning)
//        addFragment(fragmentSettings)
//        showFragment(activeFragment)

        var tasksFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NotesFragment?
        if (tasksFragment == null) {
            // Get the fragment from dagger
            tasksFragment = fragmentNotes
            addFragment(tasksFragment)
            showFragment(tasksFragment)
        }

    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(fragment_container.id, fragment, fragment.javaClass.simpleName)
            .hide(fragment)
            .commit()
    }

    private fun hideFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .hide(fragment)
            .commit()
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .show(fragment)
            .commit()
    }

}
