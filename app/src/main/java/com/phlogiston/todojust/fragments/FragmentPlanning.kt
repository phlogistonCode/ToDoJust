package com.phlogiston.todojust.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.phlogiston.todojust.R.layout

class FragmentPlanning : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout.fragment_planning, container, false)
    }
}