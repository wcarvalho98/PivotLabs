package com.ufrpe.br.pivotlabs.professional_detail.model

import com.google.firebase.database.*
import com.ufrpe.br.pivotlabs.beans.Doctor
import com.ufrpe.br.pivotlabs.beans.Schedule
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP

class ProfessionalDetailImpl(var presenter: ProfessionalDetailMVP.PresenterImpl): ProfessionalDetailMVP.ModelImpl {

    override fun getSchedules(doctorId: String) {
        val values = FirebaseDatabase.getInstance().getReference("doctors").child(doctorId)
        var doctor: Doctor
        val schedules = ArrayList<String>()

        values.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                doctor = p0.getValue(Doctor::class.java)!!
                for ((schedule, taken) in doctor.schedule!!) {
                    if (!taken) {
                        schedules.add(schedule)
                    }
                }
                getSchedule(schedules)
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    private fun getSchedule(scheduleId: ArrayList<String>) {
        val schedules = ArrayList<Schedule>()

        for (schedule in scheduleId) {
            val values = FirebaseDatabase.getInstance().getReference("schedule").child(schedule)
            values.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    schedules.add(p0.getValue(Schedule::class.java)!!)
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            })
        }
        presenter.setSchedules(schedules)
    }

}