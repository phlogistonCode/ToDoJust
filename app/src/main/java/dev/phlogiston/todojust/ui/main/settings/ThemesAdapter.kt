package dev.phlogiston.todojust.ui.main.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import dev.phlogiston.todojust.R
import dev.phlogiston.todojust.core.extensions.getCenterAbsoluteX
import dev.phlogiston.todojust.core.extensions.getCenterAbsoluteY
import dev.phlogiston.todojust.data.settings.Theme
import dev.phlogiston.todojust.data.settings.ThemeChanging
import dev.phlogiston.todojust.databinding.ItemThemeBinding

class ThemesAdapter(
    private var selected: Theme,
    val clickListener: (ThemeChanging) -> Unit
) : ListAdapter<Theme, ThemesAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_theme, parent, false)
        ) { pos, centerX, centerY ->
            val item = getItem(pos)
            val oldSelected = selected
            if (selected != item) {
                selected = item
            }
            notifyItemChanged(pos)
            notifyItemChanged(currentList.indexOf(oldSelected))
            clickListener(ThemeChanging(item, centerX, centerY))
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, selected)
    }

    class DiffCallback : DiffUtil.ItemCallback<Theme>() {
        override fun areItemsTheSame(oldItem: Theme, newItem: Theme) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Theme, newItem: Theme) =
            oldItem == newItem
    }

    class ViewHolder(
        view: View,
        private val itemClick: (Int, Int, Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private val binding by viewBinding(ItemThemeBinding::bind)

        init {
            with(binding.root) {
                setOnClickListener { itemClick(bindingAdapterPosition, getCenterAbsoluteX(), getCenterAbsoluteY()) }
            }
        }

        fun bind(item: Theme, selectedTheme: Theme?) =
            with(binding) {
                theme.setImageResource(item.icon)
                check.isInvisible = item != selectedTheme
            }
    }

}