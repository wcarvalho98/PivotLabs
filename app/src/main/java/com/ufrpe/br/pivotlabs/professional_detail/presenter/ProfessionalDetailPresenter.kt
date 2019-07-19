package com.ufrpe.br.pivotlabs.professional_detail.presenter

import android.app.Activity
import android.content.Intent
import com.ufrpe.br.pivotlabs.beans.DaySchedule
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP
import com.ufrpe.br.pivotlabs.professional_detail.model.ProfessionalDetailModel
import com.ufrpe.br.pivotlabs.professional_detail.view.ProfessionalDetailActivity

class ProfessionalDetailPresenter : ProfessionalDetailMVP.ProfessionalDetailPresenterImpl{

    lateinit var professional_id :String
    private var daySchedules: ArrayList<DaySchedule> = ArrayList<DaySchedule>()
    private lateinit var view : ProfessionalDetailMVP.ProfessionalDetailViewImpl
    private var model : ProfessionalDetailMVP.ProfessionalDetailModelImpl = ProfessionalDetailModel(this)

    override fun setView(activity: ProfessionalDetailActivity) {
        view = activity
        daySchedules = model.fetchAllSchedules()

    }

    override fun populateSchedulesList(listDaySchedules: ArrayList<DaySchedule>) {
        view.refreshScheduleList(listDaySchedules)
    }

    override fun setProfessionalId(key: String) {
        professional_id = key
    }

    override fun getProfessionalId(): String {
        return professional_id
    }

}