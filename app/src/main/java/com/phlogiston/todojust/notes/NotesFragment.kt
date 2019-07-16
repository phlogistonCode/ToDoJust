package com.phlogiston.todojust.notes

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.CompoundButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.phlogiston.todojust.R.layout
import com.phlogiston.todojust.di.scope.PerActivity
import com.phlogiston.todojust.notes.adapters.AdapterRecViewNotes
import com.phlogiston.todojust.notes.models.Note
import com.squareup.moshi.*
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_notes.*
import java.io.BufferedReader
import java.io.File
import javax.inject.Inject

@PerActivity
class NotesFragment @Inject constructor() : DaggerFragment(), NotesContract.View {
    private var values: MutableList<Note> = mutableListOf()
    private var mAdapter: AdapterRecViewNotes? = null
    private var mutableListNote = Types.newParameterizedType(MutableList::class.java, Note::class.java)
    private val jsonAdapter: JsonAdapter<MutableList<Note>> = Moshi.Builder().build().adapter(mutableListNote)

    private val onButtonClickListener = View.OnClickListener { item ->
        when (item.id) {
            btnAddNote.id -> {
                if (etAddNote.text.isNotEmpty()) {
                    values.add(0, Note(false, etAddNote.text.toString()))
                    mAdapter?.notifyItemInserted(0)
                    etAddNote.text.clear()
                    saveNotesInJson(values, "notes")
                }
            }
        }
    }

    private val onCheckBoxClickListener = CompoundButton.OnCheckedChangeListener { it, isChecked ->
        val vh = it.tag as RecyclerView.ViewHolder
        val position = vh.adapterPosition
        val thisItem = values[position]
        thisItem.check = isChecked

        if (thisItem.check) {
            val thisItemClone = thisItem.copy(check = true)
            values.removeAt(position)
            values.add(thisItemClone)
            mAdapter?.notifyItemMoved(position, values.size-1)
        } else {
            val thisItemClone = thisItem.copy(check = false)
            values.removeAt(position)
            values.add(0, thisItemClone)
            mAdapter?.notifyItemMoved(position, 0)
        }
        saveNotesInJson(values, "notes")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        values = loadJsonInNotes("notes")
        mAdapter = AdapterRecViewNotes(values)

        return inflater.inflate(layout.fragment_notes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvNotes.layoutManager = LinearLayoutManager(activity)
        rvNotes.adapter = mAdapter

        mAdapter?.setOnCheckBoxCheckedChangeListener(onCheckBoxClickListener)
        btnAddNote.setOnClickListener(onButtonClickListener)
    }

    private fun saveNotesInJson(values: MutableList<Note>, pathName: String) {
        val json = jsonAdapter.toJson(values)
        Log.i("JSON", json)
        val file = File(context?.filesDir, "$pathName.json")
        file.writeText(json)
    }

    private fun loadJsonInNotes(pathName: String): MutableList<Note> {
        val file = File(context?.filesDir, "$pathName.json")
        return if (file.exists() && file.canRead()) {
            val bufferedReader: BufferedReader = file.bufferedReader()
            val inputString = bufferedReader.use { it.readText() }
            val values = jsonAdapter.fromJson(inputString)
            Log.i("JSON", "Values: $values loaded.")

            values!!
        } else mutableListOf()
    }
}