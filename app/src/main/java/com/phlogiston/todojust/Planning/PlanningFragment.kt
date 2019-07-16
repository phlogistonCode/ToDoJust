package com.phlogiston.todojust.Planning

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.phlogiston.todojust.R.layout

class PlanningFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout.fragment_planning, container, false)
    }
}