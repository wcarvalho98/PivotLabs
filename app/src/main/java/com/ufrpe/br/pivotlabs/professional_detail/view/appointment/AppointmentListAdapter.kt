package com.ufrpe.br.pivotlabs.professional_detail.view.appointment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.beans.IndentifiedAppointment
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP
import com.ufrpe.br.pivotlabs.professional_detail.presenter.ProfessionalDetailPresenter
import com.ufrpe.br.pivotlabs.professional_detail.view.ProfessionalDetailActivity
import kotlinx.android.synthetic.main.appointment_item.view.*

class AppointmentListAdapter(var items: ArrayList<IndentifiedAppointment>,
                             val presenter: ProfessionalDetailMVP.ProfessionalDetailPresenterImpl) :
                                RecyclerView.Adapter<AppointmentListAdapter.ViewHolder>() {
    /**
     * This adapter constrols the listing of indentified appointments.
     * It displays the start and finish hours, as well as the button to choose
     * the appoitnment to be scheduled
     */


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view  = LayoutInflater.from(parent.context).inflate(R.layout.appointment_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvStart.text  = items[position].appointment.start
        holder.tvFinish.text  = items[position].appointment.finish
        holder.btnChooseAppointment.setOnClickListener{
            //Triggering the action of store the appointment scheduled in the
            //data base
            presenter.onAppointmentChosen(items[position])
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvStart = view.tvStart
        val tvFinish = view.tvFinish
        val btnChooseAppointment = view.btnChooseAppointment
    }
}