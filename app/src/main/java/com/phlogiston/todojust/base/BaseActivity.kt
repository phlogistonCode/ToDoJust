package com.phlogiston.todojust.base

import androidx.appcompat.app.AppCompatActivity
import dagger.android.support.HasSupportFragmentInjector
import dagger.android.AndroidInjector
import dagger.android.AndroidInjection
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.phlogiston.todojust.di.scope.PerActivity
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

@PerActivity
abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

}