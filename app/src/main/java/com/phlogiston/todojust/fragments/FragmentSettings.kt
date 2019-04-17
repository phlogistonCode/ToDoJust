package com.phlogiston.todojust.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import com.phlogiston.todojust.R.*

class FragmentSettings : androidx.fragment.app.Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout.fragment_settings, container, false)
    }
}