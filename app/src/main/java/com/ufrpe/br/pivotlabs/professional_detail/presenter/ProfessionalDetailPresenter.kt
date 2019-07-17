package com.ufrpe.br.pivotlabs.professional_detail.presenter

import com.ufrpe.br.pivotlabs.beans.Schedule
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP
import com.ufrpe.br.pivotlabs.professional_detail.model.ProfessionalDetailImpl
import com.ufrpe.br.pivotlabs.professional_detail.view.ProfessionalDetailActivity

class ProfessionalDetailPresenter: ProfessionalDetailMVP.PresenterImpl {

    private lateinit var schedules: ArrayList<Schedule>
    private lateinit var view: ProfessionalDetailMVP.ViewImpl
    private var model: ProfessionalDetailMVP.ModelImpl = ProfessionalDetailImpl(this)

    override fun loadData(doctorId: String) {
        model.getSchedules(doctorId)
    }

    override fun setView(view: ProfessionalDetailActivity) {
        this.view = view
    }

    override fun setSchedules(schedules: ArrayList<Schedule>) {
        this.schedules = schedules
    }

}