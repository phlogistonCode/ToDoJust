package com.phlogiston.todojust.notes.adapters

import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.phlogiston.todojust.*
import com.phlogiston.todojust.notes.models.Note


class AdapterRecViewNotes(private val values: MutableList<Note>): RecyclerView.Adapter<NotesViewHolder>() {

    var mOnCheckedChangeListener: CompoundButton.OnCheckedChangeListener? = null

    override fun getItemCount() = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycleview_notes, parent, false)
        return NotesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val item = values[position]
        holder.textView?.text = item.note

        holder.checkBox?.setOnCheckedChangeListener(null)
        holder.checkBox?.isChecked = item.check
        holder.checkBox?.tag = holder
        holder.checkBox?.setOnCheckedChangeListener(mOnCheckedChangeListener)
    }

    fun setOnCheckBoxCheckedChangeListener (checkedChangeListener: CompoundButton.OnCheckedChangeListener) {
        mOnCheckedChangeListener = checkedChangeListener
    }

}