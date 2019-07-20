package com.ufrpe.br.pivotlabs.professional_detail.view.day_period

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.beans.DayPeriod
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP
import kotlinx.android.synthetic.main.day_period_item.view.*
import kotlin.contracts.contract

class DayPeriodListAdapter(var items: ArrayList<DayPeriod>,val context: Context) :
                                RecyclerView.Adapter<DayPeriodListAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.day_period_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvDayPeriod.text = items[position].period_id
        holder.item.setOnClickListener{
            val act = context as ProfessionalDetailMVP.ProfessionalDetailViewImpl
            act.switchToAppointmentFragment(items[position].listAppointment)
        }
    }


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val item = view.cvDayPeriod
        val tvDayPeriod = view.tvDayPeriod
    }

}