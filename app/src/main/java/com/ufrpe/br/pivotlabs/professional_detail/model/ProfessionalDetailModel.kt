package com.ufrpe.br.pivotlabs.professional_detail.model

import android.os.AsyncTask
import android.widget.ArrayAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ufrpe.br.pivotlabs.beans.Appointment
import com.ufrpe.br.pivotlabs.beans.DayPeriod
import com.ufrpe.br.pivotlabs.beans.DaySchedule
import com.ufrpe.br.pivotlabs.beans.IndentifiedAppointment
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP
import kotlin.collections.ArrayList

class ProfessionalDetailModel(var presenter: ProfessionalDetailMVP.ProfessionalDetailPresenterImpl) : ProfessionalDetailMVP.ProfessionalDetailModelImpl{

    override fun fetchAllSchedules(): ArrayList<DaySchedule> = RequestSchedulesFromRemote(presenter).execute().get()


    class RequestSchedulesFromRemote(var presenter:ProfessionalDetailMVP.ProfessionalDetailPresenterImpl) : AsyncTask<Void,Void,ArrayList<DaySchedule>>(){

        private val databaseReference = FirebaseDatabase.getInstance().getReference("doctor_schedules/"+presenter.getProfessionalId())

        override fun doInBackground(vararg params: Void?): ArrayList<DaySchedule> {
            var listDaySchedule = ArrayList<DaySchedule>(5)
            databaseReference.addValueEventListener(object: ValueEventListener{

                override fun onDataChange(p0: DataSnapshot) {

                    for(date in p0.children){
                        var tempKey = date.key.toString()

                        var listDayPeriods = ArrayList<DayPeriod>(2)
                        for (day_period in date.children){
                            var tempDayPeriodKey = day_period.key.toString()

                            var listIndentifiedAppointments = ArrayList<IndentifiedAppointment>()
                            for(appointment in day_period.children){
                                var ap = appointment.getValue(Appointment::class.java)
                                listIndentifiedAppointments.add(IndentifiedAppointment(appointment.key.toString(),ap!!))
                            }
                            listDayPeriods.add(DayPeriod(tempDayPeriodKey,listIndentifiedAppointments))

                        }
                        listDaySchedule.add(DaySchedule(tempKey,listDayPeriods))
                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
            return listDaySchedule
        }

    }


}