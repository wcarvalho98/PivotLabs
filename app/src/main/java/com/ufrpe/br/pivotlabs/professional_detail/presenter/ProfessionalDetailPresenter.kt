package com.ufrpe.br.pivotlabs.professional_detail.presenter

import android.content.Intent
import androidx.fragment.app.Fragment
import com.ufrpe.br.pivotlabs.beans.*
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP
import com.ufrpe.br.pivotlabs.professional_detail.model.ProfessionalDetailModel
import com.ufrpe.br.pivotlabs.professional_detail.view.day_schedule.DayScheduleFragment
import com.ufrpe.br.pivotlabs.professional_detail.view.ProfessionalDetailActivity
import com.ufrpe.br.pivotlabs.professional_detail.view.appointment.AppointmentFragment
import com.ufrpe.br.pivotlabs.professional_detail.view.day_period.DayPeriodFragment
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP.ProfessionalDetailViewImpl.DaySchedulesFragmentImpl
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP.ProfessionalDetailViewImpl.AppointmentFragmentImpl
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP.ProfessionalDetailViewImpl.DayPeriodFragmentImpl

class ProfessionalDetailPresenter : ProfessionalDetailMVP.ProfessionalDetailPresenterImpl{


    lateinit var professional_id :String
    var doctor  = Doctor()
    private var daySchedules: ArrayList<DaySchedule> = ArrayList<DaySchedule>()
    private lateinit var view : ProfessionalDetailMVP.ProfessionalDetailViewImpl
    private lateinit var fragment: Fragment
    private var model : ProfessionalDetailMVP.ProfessionalDetailModelImpl = ProfessionalDetailModel(this)


    var date = ""
    var dayPeriod = ""
    var appointmentId = ""

    override fun setView(activity: ProfessionalDetailActivity) {
        view = activity
    }

    override fun setFragment(fragment: Fragment) {
        this.fragment = fragment
        if(this.fragment is DaySchedulesFragmentImpl){
            setDayScheduleFragment(fragment)
        }
    }

    private fun setDayScheduleFragment(fragment: Fragment) {
        this.fragment = fragment
        populateSchedulesList(model.fetchAllSchedules())
    }

    override fun populateSchedulesList(listDaySchedules: ArrayList<DaySchedule>) {
        if(fragment is DaySchedulesFragmentImpl){
            (this.fragment as DaySchedulesFragmentImpl).refreshScheduleList(listDaySchedules)
        }
    }

    override fun populateDayPeriodList(listDayPeriod: ArrayList<DayPeriod>) {
        (fragment as DayPeriodFragmentImpl).refreshDayPeriodList(listDayPeriod)
    }

    override fun populateIndentifiedAppointmentList(listIndentifiedAppointment: ArrayList<IndentifiedAppointment>) {
        (fragment as AppointmentFragmentImpl).refreshAppointmentList(listIndentifiedAppointment)
    }

    override fun setProfessionalId(key: String) {
        professional_id = key
    }

    override fun getProfessionalId(): String {
        return professional_id
    }

    override fun createDoctorObjectWithDataFromPreviousActivity(intent:Intent) {
        val b = intent.extras
        if(b != null){
            professional_id =  b.getString("professional_id")!!
            doctor.name = b.getString("professional_name")!!
            doctor.speciality = b.getString("professional_speciality")
        }
    }

    override fun getDoctorObject(): Doctor {
        return doctor
    }

    override fun onDateChosen(date: String) {
        this.date = date
    }

    override fun onDayPeriodChosen(dayPeriod: String) {
        this.dayPeriod = dayPeriod
    }

    override fun onAppointmentChosen(appointment: IndentifiedAppointment) {
        appointmentId = appointment.id
    }

}