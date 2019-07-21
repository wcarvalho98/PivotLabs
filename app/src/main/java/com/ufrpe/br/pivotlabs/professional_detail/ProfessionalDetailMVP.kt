package com.ufrpe.br.pivotlabs.professional_detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ufrpe.br.pivotlabs.beans.*
import com.ufrpe.br.pivotlabs.professional_detail.view.ProfessionalDetailActivity

interface ProfessionalDetailMVP{
    /**
     * Defining interfacese for both MVP modules and also the fragments
     * each fragment is in its own directory together with its adapter
     */

    interface ProfessionalDetailViewImpl{
        fun fillTextViewsWithDoctorData()

        /**
         * Basic intent changes
         */
        fun returnToMainActivity()

        /**
         * It is a standard convention that fragments can never comunicate with each other
         * directly, so I have created these procedures to be implemented by the activity class
         * to control the interaction among the fragments
         */
        fun initializeDayScheduleFragment()
        fun switchToDayPeriodFragment(dayPeriod: ArrayList<DayPeriod>)
        fun switchToAppointmentFragment(appointment: ArrayList<IndentifiedAppointment>)
        fun returnFromAppointmentFragment()

        /**
         * ThoseInterfaces define standard procedures to be called by the presenter in order to populate
         * the lists
         */
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

        //Auxiliary view methods showing dialogs and toasts for wen the appointment is selected
        fun showDialog()
        fun showToast(message: String)
        fun showToast(message: Int)

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
        fun onDateChosen(date:String)
        fun onDayPeriodChosen(dayPeriod: String)
        fun onAppointmentChosen(appointment: IndentifiedAppointment)
        fun saveAppointmentInRemote()
        fun cancelAppointmentSaving()
        fun markAppointmentAsTaken()
        fun makeViewShowToast(message:String)
        fun makeViewShowToast(message:Int)
    }


    interface ProfessionalDetailModelImpl{
        fun fetchAllSchedules():ArrayList<DaySchedule>
        fun storeAppointmentInRemote(patientAppointment: PatientAppointment)
    }


}