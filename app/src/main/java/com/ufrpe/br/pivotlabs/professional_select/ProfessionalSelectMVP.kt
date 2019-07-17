package com.ufrpe.br.pivotlabs.professional_select

import android.content.Intent
import com.ufrpe.br.pivotlabs.beans.Doctor
import com.ufrpe.br.pivotlabs.professional_select.view.ProfessionalSelectActivity

interface ProfessionalSelectMVP{

    interface ModelImpl{
        fun fetchAllprofessionals(): ArrayList<Doctor>
    }

    interface PresenterImpl{
        fun getHomeIntent(activity: ProfessionalSelectActivity):Intent
        fun evaluateFetchfilteredProfetionals(speciality:String, city:String, dayPeriod:String)
        fun setView(view: ProfessionalSelectActivity)
        fun getDoctors(): ArrayList<Doctor>
    }

    interface ViewImpl{
        fun returnToMainActivity()
        fun fetchFilteredProfessionals()
    }
}