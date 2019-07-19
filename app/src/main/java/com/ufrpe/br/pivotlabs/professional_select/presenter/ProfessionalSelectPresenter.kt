package com.ufrpe.br.pivotlabs.professional_select.presenter

import android.app.Activity
import android.content.Intent
import com.ufrpe.br.pivotlabs.beans.Doctor
import com.ufrpe.br.pivotlabs.professional_detail.view.ProfessionalDetailActivity
import com.ufrpe.br.pivotlabs.professional_select.ProfessionalSelectMVP
import com.ufrpe.br.pivotlabs.professional_select.model.ProfessionalSelectModel
import com.ufrpe.br.pivotlabs.professional_select.view.ProfessionalSelectActivity

class ProfessionalSelectPresenter : ProfessionalSelectMVP.PresenterImpl{

    var doctorKeys = ArrayList<String>()
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

    override fun saveDoctorKeys(keys: ArrayList<String>) {
        doctorKeys = keys
    }

    override fun populateUi(docs: ArrayList<Doctor>) {
        view.refreshDoctors(doctorKeys,docs)
    }

}