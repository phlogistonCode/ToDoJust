package com.phlogiston.todojust

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.phlogiston.todojust.R.id.*
import com.phlogiston.todojust.fragments.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val fragmentNotes = FragmentNotes()
    private val fragmentPlanning = FragmentPlanning()
    private val fragmentSettings = FragmentSettings()

    private var activeFragment: Fragment = fragmentNotes

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            navigation_notes -> {
                supportFragmentManager
                    .beginTransaction()
                    .hide(activeFragment)
                    .show(fragmentNotes)
                    .commit()

                activeFragment = fragmentNotes
                return@OnNavigationItemSelectedListener true
            }
            navigation_planning -> {
                supportFragmentManager
                    .beginTransaction()
                    .hide(activeFragment)
                    .show(fragmentPlanning)
                    .commit()

                activeFragment = fragmentPlanning
                return@OnNavigationItemSelectedListener true
            }
            navigation_settings -> {
                supportFragmentManager
                    .beginTransaction()
                    .hide(activeFragment)
                    .show(fragmentSettings)
                    .commit()

                activeFragment = fragmentSettings
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        addFragment(fragmentNotes)
        addFragment(fragmentPlanning)
        addFragment(fragmentSettings)
        showFragment(activeFragment)
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
