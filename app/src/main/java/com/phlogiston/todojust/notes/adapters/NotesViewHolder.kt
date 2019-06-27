package com.phlogiston.todojust.notes.adapters

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.phlogiston.todojust.R

class NotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var checkBox: CheckBox? = null
    var textView: TextView? = null
    init {
        checkBox = itemView.findViewById(R.id.checkbox_item_notes)
        textView = itemView.findViewById(R.id.text_item_notes)
    }
}