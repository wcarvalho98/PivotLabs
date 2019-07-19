package com.ufrpe.br.pivotlabs.professional_detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.ufrpe.br.pivotlabs.professional_detail.view.ProfessionalDetailActivity

class ProfessionalDetailMVP{

    interface ProfessionalDetailViewImpl{
        fun getDataFromPreviousActivity()
        fun fillTextViewsWithDoctorData()
        fun returnToProfessionalSelectActivity()
        fun returnToMainActivity()
    }

    interface ProfessionalDetailPresenterImpl{
        fun setProfessionalId(key:String)
        fun setView(activity: ProfessionalDetailActivity)
        fun populateSchedulesList(daySchedule : String)
    }


    interface ProfessionalDetailModelImpl{
        fun fetchAllSchedules():ArrayList<String>
    }


}