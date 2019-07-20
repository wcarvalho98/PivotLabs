package com.ufrpe.br.pivotlabs.professional_detail.view.day_schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.beans.DaySchedule
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP
import kotlinx.android.synthetic.main.days_schedule_fragment.view.*

class DayScheduleFragment(val presenter: ProfessionalDetailMVP.ProfessionalDetailPresenterImpl)
                        : Fragment(),  ProfessionalDetailMVP.ProfessionalDetailViewImpl.DaySchedulesFragmentImpl{

    companion object{
        var dayScheduleFragment : DayScheduleFragment? = null
    }

    internal lateinit var listener : OnItemSelectedListener
    private lateinit var rvSchedules : RecyclerView

    interface OnItemSelectedListener{
        fun onItemSelected(ds:DaySchedule)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.days_schedule_fragment,container,false)
        rvSchedules = view.rvSchedules
        rvSchedules.layoutManager = LinearLayoutManager(context)
        presenter.setFragment(this)
        return view
    }


    override fun refreshScheduleList(scheduleList: ArrayList<DaySchedule>) {
        rvSchedules.adapter =
            ScheduleAdapter(scheduleList, context!!)
    }

}