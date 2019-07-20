package com.ufrpe.br.pivotlabs.professional_detail.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.beans.DaySchedule
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP
import kotlinx.android.synthetic.main.days_schedule_fragment.view.*

class DayScheduleFragment(val presenter: ProfessionalDetailMVP.ProfessionalDetailPresenterImpl) : ListFragment(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.days_schedule_fragment,container,false)
        view.rvSchedules.layoutManager = LinearLayoutManager(context)

        return view
    }

    interface OnItemSelectedListener{
        fun onItemSelected(ds:DaySchedule)
    }

}