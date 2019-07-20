package com.ufrpe.br.pivotlabs.professional_detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ufrpe.br.pivotlabs.beans.*
import com.ufrpe.br.pivotlabs.professional_detail.view.ProfessionalDetailActivity

class ProfessionalDetailMVP{

    interface ProfessionalDetailViewImpl{
        fun fillTextViewsWithDoctorData()
        fun returnToMainActivity()
        fun initializeDayScheduleFragment()
        fun switchToDayPeriodFragment(dayPeriod: ArrayList<DayPeriod>)
        fun switchToAppointmentFragment(appointment: ArrayList<IndentifiedAppointment>)
        fun returnFromAppointmentFragment()
        //FragmentsInterfaces
        interface DaySchedulesFragmentImpl{
            fun refreshScheduleList(scheduleList : ArrayList<DaySchedule>)
        }

        interface DayPeriodFragmentImpl{
            fun refreshDayPeriodList(scheduleList : ArrayList<DayPeriod>)
        }

        interface AppointmentFragmentImpl{
            fun refreshAppointmentList(scheduleList : ArrayList<IndentifiedAppointment>)
        }

    }

    interface ProfessionalDetailPresenterImpl{
        fun getProfessionalId():String
        fun getDoctorObject(): Doctor
        fun setProfessionalId(key:String)
        fun setView(activity: ProfessionalDetailActivity)
        fun setFragment(fragment: Fragment)
        fun populateSchedulesList(listDaySchedules : ArrayList<DaySchedule>)
        fun populateDayPeriodList(listDayPeriod : ArrayList<DayPeriod>)
        fun populateIndentifiedAppointmentList(listIndentifiedAppointment: ArrayList<IndentifiedAppointment>)
        fun createDoctorObjectWithDataFromPreviousActivity(intent : Intent)
    }


    interface ProfessionalDetailModelImpl{
        fun fetchAllSchedules():ArrayList<DaySchedule>
    }


}