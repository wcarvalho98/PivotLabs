package com.ufrpe.br.pivotlabs.professional_detail.presenter

import android.app.Activity
import android.content.Intent
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP
import com.ufrpe.br.pivotlabs.professional_detail.model.ProfessionalDetailModel
import com.ufrpe.br.pivotlabs.professional_detail.view.ProfessionalDetailActivity

class ProfessionalDetailPresenter : ProfessionalDetailMVP.ProfessionalDetailPresenterImpl{

    lateinit var professional_id :String
    private lateinit var view : ProfessionalDetailMVP.ProfessionalDetailViewImpl
    private var model : ProfessionalDetailMVP.ProfessionalDetailModelImpl = ProfessionalDetailModel(this)

    override fun setView(activity: ProfessionalDetailActivity) {
        view = activity
    }

    override fun populateSchedulesList(daySchedule: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setProfessionalId(key: String) {
        professional_id = key
    }

}