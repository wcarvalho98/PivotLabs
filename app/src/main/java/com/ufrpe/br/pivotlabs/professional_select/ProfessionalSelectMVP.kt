package com.ufrpe.br.pivotlabs.professional_select

import android.content.Intent
import com.ufrpe.br.pivotlabs.professional_select.view.ProfessionalSelectActivity

interface ProfessionalSelectMVP{

    interface ModelImpl{
        fun fetchAllprofessionals()
    }

    interface PresenterImpl{
        fun getHomeIntent(activity: ProfessionalSelectActivity):Intent
        fun evaluateFetchfilteredProfetionals(specialty:String,city:String,dayPeriod:String)
    }

    interface ViewImpl{
        fun returnToMainActivity()
        fun fetchFilteredProfessionals()
    }
}