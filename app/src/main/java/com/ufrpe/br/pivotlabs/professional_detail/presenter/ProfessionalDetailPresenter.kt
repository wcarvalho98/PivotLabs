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
import java.security.KeyStore
import java.security.MessageDigest

class ProfessionalDetailPresenter : ProfessionalDetailMVP.ProfessionalDetailPresenterImpl{

    /**Implementation of the interface ProfessioanlDetailPresenterImpl
     */

    lateinit var professional_id :String
    var doctor  = Doctor()
    //Object to keep track of the appointment chosen
    var chosenAppointment : IndentifiedAppointment? = null//auxiliary variable to be used is the storing of the patient
    //appointment is successful
    var patientAppointment = PatientAppointment()
    private var daySchedules: ArrayList<DaySchedule> = ArrayList<DaySchedule>()
    private lateinit var view : ProfessionalDetailMVP.ProfessionalDetailViewImpl
    private lateinit var fragment: Fragment
    private var model : ProfessionalDetailMVP.ProfessionalDetailModelImpl = ProfessionalDetailModel(this)


    override fun setView(activity: ProfessionalDetailActivity) {
        view = activity
    }

    override fun setFragment(fragment: Fragment) {
        this.fragment = fragment
        //Verifies is the fragment set is an instance of DaySchedulesFragmentImpl
        // if true fetches the day schedules from remote
        if(this.fragment is DaySchedulesFragmentImpl){
            setDayScheduleFragment(fragment)
        }
    }

    private fun setDayScheduleFragment(fragment: Fragment) {
        this.fragment = fragment
        populateSchedulesList(model.fetchAllSchedules())
    }

    //Populating listing of day schedule fragment
    override fun populateSchedulesList(listDaySchedules: ArrayList<DaySchedule>) {
        if(fragment is DaySchedulesFragmentImpl){
            (this.fragment as DaySchedulesFragmentImpl).refreshScheduleList(listDaySchedules)
        }
    }

    //Populating the listing of DayPeriodFragment
    override fun populateDayPeriodList(listDayPeriod: ArrayList<DayPeriod>) {
        (fragment as DayPeriodFragmentImpl).refreshDayPeriodList(listDayPeriod)
    }

    //Populating the listing the AppointmentFragmen
    override fun populateIndentifiedAppointmentList(listIndentifiedAppointment: ArrayList<IndentifiedAppointment>) {
        (fragment as AppointmentFragmentImpl).refreshAppointmentList(listIndentifiedAppointment)
    }


    //Building auxiliary objects to be used in the storing
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
            patientAppointment.doctorId = professional_id
        }
    }

    override fun getDoctorObject(): Doctor {
        return doctor
    }

    //Methods destined to be called when all the different elements of the appointment are being chosen
    override fun onDateChosen(date: String) {
        this.patientAppointment.date = date
    }

    override fun onDayPeriodChosen(dayPeriod: String) {
        this.patientAppointment.dayPeriod = dayPeriod
    }

    //Here, The appointment object of the appointment chosen has already been built
    //therefore it can be stored in remote
    override fun onAppointmentChosen(appointment: IndentifiedAppointment) {
        chosenAppointment = appointment
        view.showDialog()
        this.patientAppointment.apointmentId  = chosenAppointment!!.id
    }

    override fun saveAppointmentInRemote() {
        model.storeAppointmentInRemote(this.patientAppointment)
    }

    override fun cancelAppointmentSaving() {
        chosenAppointment = null
        this.patientAppointment.apointmentId = ""
    }

    //When the storing of the appointment is successful the appointment is marked as taken
    //and it is updated in the database
    override fun markAppointmentAsTaken() {
        chosenAppointment!!.appointment.taken = true
        model.updateAppointmentInRemote(professional_id,
                                        patientAppointment.date,
                                        patientAppointment.dayPeriod,
                                        chosenAppointment!!)
    }

    override fun makeViewShowToast(message: String) {
        view.showToast(message)
    }

    override fun makeViewShowToast(message: Int) {
        view.showToast(message)
    }
}