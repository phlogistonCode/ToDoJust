package dev.phlogiston.todojust.ui.main.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.phlogiston.todojust.R
import dev.phlogiston.todojust.databinding.ItemTaskBinding
import dev.phlogiston.todojust.db.tasks.Task

class EventsAdapter : ListAdapter<Task, EventsAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_task, parent, false)
        ) { pos ->

        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Task, newItem: Task) =
            oldItem == newItem
    }

    class ViewHolder(
        view: View,
        private val itemClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private val binding by viewBinding(ItemTaskBinding::bind)

        init {
            binding.root.setOnClickListener { itemClick(bindingAdapterPosition) }
        }

        fun bind(item: Task) =
            with(binding) {
                text.text = item.text
            }
    }

}