package com.ufrpe.br.pivotlabs.professional_select

import android.app.Activity
import android.content.Intent
import com.ufrpe.br.pivotlabs.beans.Doctor
import com.ufrpe.br.pivotlabs.professional_select.view.ProfessionalSelectActivity

interface ProfessionalSelectMVP{

    interface ModelImpl{
        fun fetchAllprofessionals(): ArrayList<Doctor>
    }

    interface PresenterImpl{
        fun saveDoctorKeys(keys:ArrayList<String>)
        fun populateUi(docs: ArrayList<Doctor>)
        fun setView(view: ProfessionalSelectActivity)
        fun evaluateFetchfilteredProfetionals(speciality: String)
    }

    interface ViewImpl{
        fun returnToMainActivity()
        fun fetchFilteredProfessionals()
        fun refreshDoctors(doctorKeys:ArrayList<String>,doctors: ArrayList<Doctor>)
    }
}