package com.ufrpe.br.pivotlabs.professional_detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.ufrpe.br.pivotlabs.beans.DaySchedule
import com.ufrpe.br.pivotlabs.professional_detail.view.ProfessionalDetailActivity

class ProfessionalDetailMVP{

    interface ProfessionalDetailViewImpl{
        fun getDataFromPreviousActivity()
        fun fillTextViewsWithDoctorData()
        fun returnToProfessionalSelectActivity()
        fun returnToMainActivity()
        fun refreshScheduleList(scheduleList : ArrayList<DaySchedule>)
    }

    interface ProfessionalDetailPresenterImpl{
        fun getProfessionalId():String
        fun setProfessionalId(key:String)
        fun setView(activity: ProfessionalDetailActivity)
        fun populateSchedulesList(listDaySchedules : ArrayList<DaySchedule>)
    }


    interface ProfessionalDetailModelImpl{
        fun fetchAllSchedules():ArrayList<DaySchedule>
    }


}