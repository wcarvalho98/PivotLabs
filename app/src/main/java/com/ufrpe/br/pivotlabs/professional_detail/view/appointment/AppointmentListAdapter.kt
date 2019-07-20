package com.ufrpe.br.pivotlabs.professional_detail.view.appointment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.beans.IndentifiedAppointment
import kotlinx.android.synthetic.main.appointment_item.view.*

class AppointmentListAdapter(var items: ArrayList<IndentifiedAppointment>) :
                                RecyclerView.Adapter<AppointmentListAdapter.ViewHolder>() {



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
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvStart = view.tvStart
        val tvFinish = view.tvFinish
        val btnChooseAppointment = view.btnChooseAppointment
    }
}