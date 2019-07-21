package com.ufrpe.br.pivotlabs.professional_detail.model

import android.os.AsyncTask
import android.widget.ArrayAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ufrpe.br.pivotlabs.beans.*
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP
import kotlin.collections.ArrayList

class ProfessionalDetailModel(var presenter: ProfessionalDetailMVP.ProfessionalDetailPresenterImpl) : ProfessionalDetailMVP.ProfessionalDetailModelImpl{


    private var user =  FirebaseAuth.getInstance()
    private var patientAppointmentRef = FirebaseDatabase.getInstance().getReference("patient_appointment")


    override fun fetchAllSchedules(): ArrayList<DaySchedule> = RequestSchedulesFromRemote(presenter).execute().get()


    override fun storeAppointmentInRemote(patientAppointment: PatientAppointment) {
        patientAppointmentRef.child(user.currentUser!!.uid).setValue(patientAppointment)
            .addOnCompleteListener{
                if(it.isSuccessful){

                }
                else{

                }
            }
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

}