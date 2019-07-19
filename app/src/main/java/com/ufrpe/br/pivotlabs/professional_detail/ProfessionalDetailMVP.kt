package com.ufrpe.br.pivotlabs.professional_detail

import android.app.Activity
import android.content.Intent
import com.ufrpe.br.pivotlabs.professional_detail.view.ProfessionalDetailActivity

class ProfessionalDetailMVP{

    interface ProfessionalDetailViewImpl{
        fun returnToProfessionalSelectActivity()
        fun returnToMainActivity()
    }

    interface ProfessionalDetailPresenterImpl{
        fun setView(activity: ProfessionalDetailActivity)
        fun populateSchedulesList(daySchedule : String)
        fun getProfessionalSelectActivityIntent(activity : Activity): Intent
        fun getMainActivityIntent(activity: Activity): Intent
    }


    interface ProfessionalDetailModelImpl{
        fun fetchAllSchedules():ArrayList<String>
    }


}