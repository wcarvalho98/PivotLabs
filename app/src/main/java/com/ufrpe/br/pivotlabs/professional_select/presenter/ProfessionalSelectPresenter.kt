package com.ufrpe.br.pivotlabs.professional_select.presenter

import android.content.Intent
import com.ufrpe.br.pivotlabs.beans.Doctor
import com.ufrpe.br.pivotlabs.main.view.MainActivity
import com.ufrpe.br.pivotlabs.professional_select.ProfessionalSelectMVP
import com.ufrpe.br.pivotlabs.professional_select.model.ProfessionalSelectModel
import com.ufrpe.br.pivotlabs.professional_select.view.ProfessionalSelectActivity

class ProfessionalSelectPresenter : ProfessionalSelectMVP.PresenterImpl{

    private var doctors = ArrayList<Doctor>()
    private lateinit var view: ProfessionalSelectMVP.ViewImpl
    private var model: ProfessionalSelectMVP.ModelImpl = ProfessionalSelectModel(this)

    override fun evaluateFetchfilteredProfetionals(speciality: String) {
        populateUi(filterBySpeciality(speciality))
    }

    override fun setView(view: ProfessionalSelectActivity) {
        this.view = view
        this.doctors = model.fetchAllprofessionals()
    }

    private fun filterBySpeciality(speciality: String): ArrayList<Doctor> {
        return if (speciality == "All")
            doctors
        else {
            val docs = ArrayList<Doctor>()
            for (doc in doctors) {
                if (doc.speciality.equals(speciality))
                    docs.add(doc)
            }
            docs
        }
    }

    override fun populateUi(docs: ArrayList<Doctor>) {
        view.refreshDoctors(docs)
    }
    override fun getHomeIntent(activity: ProfessionalSelectActivity): Intent {
        return Intent(activity, MainActivity::class.java)
    }

}