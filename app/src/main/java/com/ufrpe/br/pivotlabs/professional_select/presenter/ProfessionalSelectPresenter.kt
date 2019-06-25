package com.ufrpe.br.pivotlabs.professional_select.presenter

import android.content.Intent
import com.ufrpe.br.pivotlabs.main.view.MainActivity
import com.ufrpe.br.pivotlabs.professional_select.ProfessionalSelectMVP
import com.ufrpe.br.pivotlabs.professional_select.view.ProfessionalSelectActivity

class ProfessionalSelectPresenter : ProfessionalSelectMVP.PresenterImpl{

    override fun evaluateFetchfilteredProfetionals(specialty: String, city: String, dayPeriod: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getHomeIntent(activity: ProfessionalSelectActivity): Intent {
        return Intent(activity,MainActivity::class.java)
    }

}