package com.ufrpe.br.pivotlabs.professional_detail.view.appointment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.beans.Appointment
import com.ufrpe.br.pivotlabs.beans.IndentifiedAppointment
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP
import kotlinx.android.synthetic.main.fragment_appointment.view.*

class AppointmentFragment (var listAppointmets : ArrayList<IndentifiedAppointment>):
        Fragment(), ProfessionalDetailMVP.ProfessionalDetailViewImpl.AppointmentFragmentImpl{


    lateinit var rvAppointments : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_appointment,container,false)
        rvAppointments = view.rvAppointments
        return view
    }

    override fun refreshAppointmentList(scheduleList: ArrayList<IndentifiedAppointment>) {
        rvAppointments.adapter = AppointmentListAdapter(scheduleList)
    }
}