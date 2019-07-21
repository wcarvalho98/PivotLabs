package com.ufrpe.br.pivotlabs.professional_detail.model

import android.os.AsyncTask
import android.widget.ArrayAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.beans.*
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP
import kotlin.collections.ArrayList

class ProfessionalDetailModel(var presenter: ProfessionalDetailMVP.ProfessionalDetailPresenterImpl) : ProfessionalDetailMVP.ProfessionalDetailModelImpl{



    private var user =  FirebaseAuth.getInstance()
    private var patientAppointmentRef = FirebaseDatabase.getInstance().getReference("patient_appointment")


    override fun fetchAllSchedules(): ArrayList<DaySchedule> = RequestSchedulesFromRemote(presenter).execute().get()


    override fun fetchAllAvailableDoctorAppointments(
        doctorId: String,
        date: String,
        dayPeriod: String
    ): ArrayList<IndentifiedAppointment> = RequestAvailableAppointmentsFromRemote(doctorId,date,dayPeriod,presenter).execute().get()


    /**
     * This method performs the storing of the appointment in the remote database
     */
    override fun storeAppointmentInRemote(patientAppointment: PatientAppointment) {
        patientAppointmentRef.child(user.currentUser!!.uid).push().setValue(patientAppointment)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    presenter.makeViewShowToast(R.string.text_storing_success)
                    presenter.markAppointmentAsTaken()
                }
                else{
                    presenter.makeViewShowToast(R.string.text_storing_problem)
                    presenter.cancelAppointmentSaving()
                }
            }
    }

    /**
     * This method updates the status of a specific appointment chosen by the user
     * by building the path to it on a string
     * and the setting a new value for the child element: "taken"
     */
    override fun updateAppointmentInRemote(
        doctorId: String,
        date: String,
        dayPeriod: String,
        indentifiedAp: IndentifiedAppointment
    ) {
        val pathToAp = "doctor_schedules/"+doctorId+"/"+date+"/"+dayPeriod+"/"+indentifiedAp.id
        val dbRef = FirebaseDatabase.getInstance().getReference(pathToAp)
        dbRef.child("taken").setValue(indentifiedAp.appointment.taken)
    }

    class RequestSchedulesFromRemote(var presenter:ProfessionalDetailMVP.ProfessionalDetailPresenterImpl) : AsyncTask<Void,Void,ArrayList<DaySchedule>>(){

        //Getting the reference from the remote with the element of the professional with a
        //specific ID
        private val databaseReference = FirebaseDatabase.getInstance().getReference("doctor_schedules/"+presenter.getProfessionalId())

        /**
         * This method builds an ArrayList of DayShedule objects and returns them.
         * it buids the ArrayListObjects by iterating through the children
         * of the scheduel elements of a professional with a specific ID
         */
        override fun doInBackground(vararg params: Void?): ArrayList<DaySchedule> {
            var listDaySchedule = ArrayList<DaySchedule>(5)
            databaseReference.addValueEventListener(object: ValueEventListener{

                override fun onDataChange(p0: DataSnapshot) {

                    for(date in p0.children){
                        val tempKey = date.key.toString()

                        val listDayPeriods = ArrayList<DayPeriod>(2)
                        for (day_period in date.children){
                            val tempDayPeriodKey = day_period.key.toString()

                            val listIndentifiedAppointments = ArrayList<IndentifiedAppointment>()
                            for(apt in day_period.children){
                                val ap = apt.getValue(Appointment::class.java)
                                listIndentifiedAppointments.add(IndentifiedAppointment(apt.key.toString(),ap!!))
                            }
                            listDayPeriods.add(DayPeriod(tempDayPeriodKey,listIndentifiedAppointments))

                        }
                        listDaySchedule.add(DaySchedule(tempKey,listDayPeriods))
                    }
                    presenter.populateSchedulesList(listDaySchedule)
                }

                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })

            presenter.populateSchedulesList(listDaySchedule)
            return listDaySchedule
        }
    }

    class RequestAvailableAppointmentsFromRemote(
                                                val doctorId: String,
                                                val date: String,
                                                val dayPeriod: String,
                                                val presenter : ProfessionalDetailMVP.ProfessionalDetailPresenterImpl)
                                                : AsyncTask<Void,Void,ArrayList<IndentifiedAppointment>>(){
        private val dataBaseRef = FirebaseDatabase.getInstance().getReference("doctor_schedules/"+
                                                                              doctorId+"/"+
                                                                                date+"/"+
                                                                                dayPeriod)

        override fun doInBackground(vararg params: Void?): ArrayList<IndentifiedAppointment> {
            var values = ArrayList<IndentifiedAppointment>(5)
            dataBaseRef.addValueEventListener(object: ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot) {

                    for(child in p0.children){
                        val key = child.key.toString()
                        values.add(IndentifiedAppointment(key,child.getValue(Appointment::class.java)!!))

                    }

                    presenter.populateIndentifiedAppointmentList(values)
                }

            })
            presenter.populateIndentifiedAppointmentList(values)
            return values
        }

    }

}