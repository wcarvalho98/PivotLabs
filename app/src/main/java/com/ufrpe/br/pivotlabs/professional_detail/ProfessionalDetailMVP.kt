package com.ufrpe.br.pivotlabs.professional_detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ufrpe.br.pivotlabs.beans.Appointment
import com.ufrpe.br.pivotlabs.beans.DayPeriod
import com.ufrpe.br.pivotlabs.beans.DaySchedule
import com.ufrpe.br.pivotlabs.beans.Doctor
import com.ufrpe.br.pivotlabs.professional_detail.view.ProfessionalDetailActivity

class ProfessionalDetailMVP{

    interface ProfessionalDetailViewImpl{
        fun fillTextViewsWithDoctorData()
        fun returnToProfessionalSelectActivity()
        fun returnToMainActivity()


        //FragmentsInterfaces
        interface DaySchedulesFragmentImpl{
            fun refreshScheduleList(scheduleList : ArrayList<DaySchedule>)
        }

        interface DayPeriodFragmentImpl{
            fun refreshDayPeriodList(scheduleList : ArrayList<DayPeriod>)
        }

        interface AppointmentFragmentImpl{
            fun refreshAppointmentList(scheduleList : ArrayList<Appointment>)
        }

    }

    interface ProfessionalDetailPresenterImpl{
        fun getProfessionalId():String
        fun getDoctorObject(): Doctor
        fun setProfessionalId(key:String)
        fun setView(activity: ProfessionalDetailActivity)
        fun setDayScheduleFragment(fragment: Fragment)
        fun populateSchedulesList(listDaySchedules : ArrayList<DaySchedule>)
        fun createDoctorObjectWithDataFromPreviousActivity(intent : Intent)
    }


    interface ProfessionalDetailModelImpl{
        fun fetchAllSchedules():ArrayList<DaySchedule>
    }


}