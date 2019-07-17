package com.ufrpe.br.pivotlabs.professional_detail

import com.ufrpe.br.pivotlabs.beans.Schedule
import com.ufrpe.br.pivotlabs.professional_detail.view.ProfessionalDetailActivity

interface ProfessionalDetailMVP{

    interface ModelImpl{
        fun getSchedules(doctorId: String)
    }

    interface PresenterImpl{
        fun setView(view: ProfessionalDetailActivity)
        fun loadData(doctorId: String)
        fun setSchedules(schedules: ArrayList<Schedule>)
    }

    interface ViewImpl{
    }
}