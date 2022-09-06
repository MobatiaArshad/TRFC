package com.a71cities.trfc.views.events

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.a71cities.currencyconverter.extras.BaseFragment
import com.a71cities.trfc.R
import com.a71cities.trfc.databinding.CalendarDayLayoutBinding
import com.a71cities.trfc.databinding.FragmentEventBinding
import com.a71cities.trfc.databinding.MonthHeaderBinding
import com.a71cities.trfc.utils.wordCapitalize
import com.a71cities.trfc.views.events.adapter.TeamVsTeamAdapter
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.WeekFields
import java.util.*

class EventFragment : BaseFragment() {

    companion object {
        fun newInstance() = EventFragment()
    }

    override lateinit var viewModel: EventViewModel
    private lateinit var binding: FragmentEventBinding
    private val selectedDates = mutableSetOf<LocalDate>()
    private val today = LocalDate.now()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[EventViewModel::class.java]

        viewModel.matchData.observe(viewLifecycleOwner) {
            binding.recycler.adapter = TeamVsTeamAdapter(it)
        }

        setUpCalendar()



    }

    private fun setUpCalendar() {
        val currentMonth = YearMonth.now()
        val firstMonth = currentMonth.minusMonths(0)
        val lastMonth = currentMonth.plusMonths(1)
        val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
        binding.calendarView.setup(firstMonth, lastMonth, firstDayOfWeek)
        binding.calendarView.scrollToMonth(currentMonth)


        class DayViewContainer(view: View) : ViewContainer(view) {
            // Will be set when this container is bound. See the dayBinder.
            lateinit var day: CalendarDay
            val textView = CalendarDayLayoutBinding.bind(view).calendarDayText

            init {
                view.setOnClickListener {
                    if (day.owner == DayOwner.THIS_MONTH) {
                        if (selectedDates.contains(day.date)) {
                            selectedDates.remove(day.date)
                        } else {
                            selectedDates.add(day.date)
                            Toast.makeText(context, ""+day.date, Toast.LENGTH_SHORT).show()
                        }
                        binding.calendarView.notifyDayChanged(day)
                    }
                }
            }
        }


        binding.calendarView.dayBinder = object : DayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.day = day
                val textView = container.textView
                textView.text = day.date.dayOfMonth.toString()

                if (day.owner == DayOwner.THIS_MONTH) {
                    when {
                        selectedDates.contains(day.date) -> {
                            textView.setTextColor(ContextCompat.getColor(context!!,R.color.backround_light_red))
                            textView.setBackgroundResource(R.drawable.calender_selected_bg)
                        }
                        today == day.date -> {
                            textView.setTextColor(ContextCompat.getColor(context!!,R.color.black))
                            textView.setBackgroundResource(R.color.background_red)
                            textView.setTypeface(textView.typeface, Typeface.BOLD)

                        }
                        else -> {
                            textView.setTextColor(ContextCompat.getColor(context!!,R.color.white))
                            textView.background = null
                        }
                    }
                } else {
                    textView.setTextColor(ContextCompat.getColor(context!!,R.color.trans_white))
                    textView.background = null
                }
            }
        }

        class MonthViewContainer(view: View) : ViewContainer(view) {
            val textView = MonthHeaderBinding.bind(view).monthTxt
        }

        binding.calendarView.monthHeaderBinder = object : MonthHeaderFooterBinder<MonthViewContainer> {
            override fun create(view: View) = MonthViewContainer(view)
            override fun bind(container: MonthViewContainer, month: CalendarMonth) {
                container.textView.text = "${month.yearMonth.month.name.wordCapitalize()} ${month.year}"
            }
        }

    }


}