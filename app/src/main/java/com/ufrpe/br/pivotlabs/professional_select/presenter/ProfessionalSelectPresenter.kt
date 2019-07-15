package com.ufrpe.br.pivotlabs.professional_select.presenter

import android.content.Intent
import com.ufrpe.br.pivotlabs.beans.Doctor
import com.ufrpe.br.pivotlabs.main.view.MainActivity
import com.ufrpe.br.pivotlabs.professional_select.ProfessionalSelectMVP
import com.ufrpe.br.pivotlabs.professional_select.model.ProfessionalSelectModel
import com.ufrpe.br.pivotlabs.professional_select.view.ProfessionalSelectActivity

class ProfessionalSelectPresenter : ProfessionalSelectMVP.PresenterImpl{

    private var model: ProfessionalSelectMVP.ModelImpl = ProfessionalSelectModel(this)
    private lateinit var view: ProfessionalSelectMVP.ViewImpl
    private lateinit var doctors: ArrayList<Doctor>

    override fun evaluateFetchfilteredProfetionals(speciality: String, city: String, dayPeriod: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setView(view: ProfessionalSelectActivity) {
        this.view = view
        doctors = model.fetchAllprofessionals()
    }

    override fun getHomeIntent(activity: ProfessionalSelectActivity): Intent {
        return Intent(activity, MainActivity::class.java)
    }

}