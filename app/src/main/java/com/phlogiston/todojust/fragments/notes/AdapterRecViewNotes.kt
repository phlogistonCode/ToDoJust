package com.phlogiston.todojust.fragments.notes

import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView


class AdapterRecViewNotes(private val values: MutableList<Note>): RecyclerView.Adapter<AdapterRecViewNotes.ViewHolder>() {

    var mOnCheckedChangeListener: CompoundButton.OnCheckedChangeListener? = null

    override fun getItemCount() = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(com.phlogiston.todojust.R.layout.item_recycleview_notes, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var checkBox: CheckBox? = null
        var textView: TextView? = null
        init {
            checkBox = itemView.findViewById(com.phlogiston.todojust.R.id.checkbox_item_notes)
            textView = itemView.findViewById(com.phlogiston.todojust.R.id.text_item_notes)
        }
    }

}