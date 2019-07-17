package com.ufrpe.br.pivotlabs.professional_select

import android.content.Intent
import com.ufrpe.br.pivotlabs.beans.Doctor
import com.ufrpe.br.pivotlabs.professional_select.view.ProfessionalSelectActivity

interface ProfessionalSelectMVP{

    interface ModelImpl{
        fun fetchAllprofessionals(): ArrayList<Doctor>
    }

    interface PresenterImpl{
        fun populateUi(docs: ArrayList<Doctor>)
        fun setView(view: ProfessionalSelectActivity)
        fun evaluateFetchfilteredProfetionals(speciality: String)
        fun getHomeIntent(activity: ProfessionalSelectActivity): Intent
    }

    interface ViewImpl{
        fun returnToMainActivity()
        fun fetchFilteredProfessionals()
        fun refreshDoctors(doctors: ArrayList<Doctor>)
    }
}