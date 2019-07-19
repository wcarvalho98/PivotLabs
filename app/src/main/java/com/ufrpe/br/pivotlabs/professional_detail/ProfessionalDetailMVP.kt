package com.ufrpe.br.pivotlabs.professional_detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.ufrpe.br.pivotlabs.beans.DaySchedule
import com.ufrpe.br.pivotlabs.beans.Doctor
import com.ufrpe.br.pivotlabs.professional_detail.view.ProfessionalDetailActivity

class ProfessionalDetailMVP{

    interface ProfessionalDetailViewImpl{
        fun fillTextViewsWithDoctorData()
        fun returnToProfessionalSelectActivity()
        fun returnToMainActivity()
        fun refreshScheduleList(scheduleList : ArrayList<DaySchedule>)
    }

    interface ProfessionalDetailPresenterImpl{
        fun getProfessionalId():String
        fun getDoctorObject(): Doctor
        fun setProfessionalId(key:String)
        fun setView(activity: ProfessionalDetailActivity)
        fun populateSchedulesList(listDaySchedules : ArrayList<DaySchedule>)
        fun createDoctorObjectWithDataFromPreviousActivity(intent : Intent)
    }


    interface ProfessionalDetailModelImpl{
        fun fetchAllSchedules():ArrayList<DaySchedule>
    }


}