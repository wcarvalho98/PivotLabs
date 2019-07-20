package com.ufrpe.br.pivotlabs.professional_detail.view.day_period

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.beans.DayPeriod
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP
import com.ufrpe.br.pivotlabs.professional_detail.presenter.ProfessionalDetailPresenter
import com.ufrpe.br.pivotlabs.professional_detail.view.ProfessionalDetailActivity
import kotlinx.android.synthetic.main.day_period_item.view.*
import kotlin.contracts.contract

class DayPeriodListAdapter(var items: ArrayList<DayPeriod>,
                           val presenter: ProfessionalDetailMVP.ProfessionalDetailPresenterImpl,
                           val view: ProfessionalDetailMVP.ProfessionalDetailViewImpl) :
                                RecyclerView.Adapter<DayPeriodListAdapter.ViewHolder>(){

    /**
     * Adapter that helps controlling the day period selection
     */

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
            //Setting dayperiod of the PatientAppointment to be saved in the backend
            presenter.onDayPeriodChosen(items[position].period_id)
            //Here an also in the other switch methord the instance ViewImpl acts as a listener
            // for sending data to other fragments
            view.switchToAppointmentFragment(items[position].listAppointment)
        }
    }


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val item = view.cvDayPeriod
        val tvDayPeriod = view.tvDayPeriod
    }

}