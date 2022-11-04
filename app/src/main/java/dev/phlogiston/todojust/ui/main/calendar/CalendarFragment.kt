package dev.phlogiston.todojust.ui.main.calendar

import android.view.View
import android.widget.TextView
import androidx.core.view.children
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder
import com.kizitonwose.calendar.view.ViewContainer
import dev.phlogiston.todojust.R
import dev.phlogiston.todojust.core.base.BaseFragment
import dev.phlogiston.todojust.core.base.BaseMainFragment
import dev.phlogiston.todojust.core.extensions.invisible
import dev.phlogiston.todojust.core.extensions.setColor
import dev.phlogiston.todojust.core.extensions.visible
import dev.phlogiston.todojust.databinding.CalendarDayItemBinding
import dev.phlogiston.todojust.databinding.CalendarHeaderBinding
import dev.phlogiston.todojust.databinding.FragmentCalendarBinding
import dev.phlogiston.todojust.databinding.FragmentSettingsBinding
import dev.phlogiston.todojust.db.notes.Note
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CalendarFragment : BaseMainFragment(R.layout.fragment_calendar) {

    private val binding by viewBinding(FragmentCalendarBinding::bind)

    private var selectedDate: LocalDate? = null
    private val today = LocalDate.now()

    private val eventsAdapter = EventsAdapter()
    private val selectionFormatter = DateTimeFormatter.ofPattern("d MMM yyyy")

    override fun bind() {
    }

    override fun initViews(view: View) {
        binding.events.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = eventsAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        }

        binding.calendar.monthScrollListener = {
            // Select the first day of the visible month.
            selectDate(it.yearMonth.atDay(1))
        }

        val daysOfWeek = daysOfWeek()
        val currentMonth = YearMonth.now()
        val startMonth = currentMonth.minusMonths(50)
        val endMonth = currentMonth.plusMonths(50)
        configureBinders(daysOfWeek)
        binding.calendar.apply {
            setup(startMonth, endMonth, daysOfWeek.first())
            scrollToMonth(currentMonth)
        }
    }

    private fun selectDate(date: LocalDate) {
        if (selectedDate != date) {
            val oldDate = selectedDate
            selectedDate = date
            oldDate?.let { binding.calendar.notifyDateChanged(it) }
            binding.calendar.notifyDateChanged(date)
            updateAdapterForDate(date)
        }
    }

    private fun updateAdapterForDate(date: LocalDate) {
        eventsAdapter.submitList(listOf(Note(text = "test")))
        binding.selectedDate.text = selectionFormatter.format(date)
    }

    private fun configureBinders(daysOfWeek: List<DayOfWeek>) {
        class DayViewContainer(view: View) : ViewContainer(view) {
            lateinit var day: CalendarDay // Will be set when this container is bound.
            val binding = CalendarDayItemBinding.bind(view)

            init {
                view.setOnClickListener {
                    if (day.position == DayPosition.MonthDate) {
                        selectDate(day.date)
                    }
                }
            }
        }
        binding.calendar.dayBinder = object : MonthDayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, data: CalendarDay) {
                container.day = data
                val textView = container.binding.root

                textView.text = data.date.dayOfMonth.toString()

                if (data.position == DayPosition.MonthDate) {
                    textView.visible()
                    when (data.date) {
                        today -> {
                            textView.setColor(R.color.onPrimary)
                            textView.setBackgroundResource(R.drawable.bg_day_today)
                        }
                        selectedDate -> {
                            textView.setColor(R.color.onSecondary)
                            textView.setBackgroundResource(R.drawable.bg_day_selected)
                        }
                        else -> {
                            textView.setColor(R.color.primary)
                            textView.background = null
                        }
                    }
                } else {
                    textView.invisible()
                }
            }
        }

        class MonthViewContainer(view: View) : ViewContainer(view) {
            val legendLayout = CalendarHeaderBinding.bind(view).root
        }
        binding.calendar.monthHeaderBinder =
            object : MonthHeaderFooterBinder<MonthViewContainer> {
                override fun create(view: View) = MonthViewContainer(view)
                override fun bind(container: MonthViewContainer, data: CalendarMonth) {
                    // Setup each header day text if we have not done that already.
                    if (container.legendLayout.tag == null) {
                        container.legendLayout.tag = data.yearMonth
                        container.legendLayout.children.map { it as TextView }
                            .forEachIndexed { index, tv ->
                                tv.text = daysOfWeek[index].name.first().toString()
                                tv.setColor(R.color.primary)
                            }
                    }
                }
            }
    }
}